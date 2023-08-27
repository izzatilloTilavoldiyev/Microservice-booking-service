package uz.pdp.bookingservice.service.save;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uz.pdp.bookingservice.dto.request.SaveRequestDTO;
import uz.pdp.bookingservice.dto.response.SaveResponseDTO;
import uz.pdp.bookingservice.entity.Apartment;
import uz.pdp.bookingservice.entity.Save;
import uz.pdp.bookingservice.entity.User;
import uz.pdp.bookingservice.exception.DataNotFoundException;
import uz.pdp.bookingservice.exception.DuplicateDataException;
import uz.pdp.bookingservice.repository.ApartmentRepository;
import uz.pdp.bookingservice.repository.SaveRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaveServiceImpl implements SaveService{

    private final RestTemplate restTemplate;

    private final ApartmentRepository apartmentRepository;

    private final SaveRepository saveRepository;

    private final ModelMapper modelMapper;

    @Value("${user-service.url}")
    private String userServiceUrl;

    @Override
    public SaveResponseDTO addToSave(SaveRequestDTO saveRequestDTO) {
        checkSaveUnique(saveRequestDTO.getUserId(), saveRequestDTO.getApartmentId());
        try {
            User user = restTemplate.getForObject(userServiceUrl + "/get/"
                    + saveRequestDTO.getUserId(), User.class);
            Apartment apartment = apartmentRepository.findActiveApartmentById(saveRequestDTO.getApartmentId())
                    .orElseThrow(
                            () -> new DataNotFoundException("Apartment not found with ID: " + saveRequestDTO.getApartmentId())
                    );
            Save save = Save.builder()
                    .userId(user.getId())
                    .apartment(apartment)
                    .build();
            saveRepository.save(save);
            return modelMapper.map(save, SaveResponseDTO.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new DataNotFoundException("User not found with ID: " + saveRequestDTO.getUserId());
        }

    }

    private void checkSaveUnique(UUID userID, UUID apartmentID) {
        if (saveRepository.existsByUserIdAndApartmentId(userID, apartmentID))
            throw new DuplicateDataException(
                    "Apartment has already saved with userID: " +userID+ " and apartmentID: " + apartmentID);
    }

}