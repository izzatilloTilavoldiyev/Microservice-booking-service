package uz.pdp.bookingservice.service.save;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import uz.pdp.bookingservice.service.apartment.ApartmentServiceImpl;
import uz.pdp.bookingservice.service.user.UserService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaveServiceImpl implements SaveService {

    private final ApartmentServiceImpl apartmentService;

    private final SaveRepository saveRepository;

    private final ModelMapper modelMapper;

    private final UserService userService;

    @Override
    public SaveResponseDTO addToSave(SaveRequestDTO saveRequestDTO) {
        checkSaveUnique(saveRequestDTO.getUserId(), saveRequestDTO.getApartmentId());

        User user = userService.getUserById(saveRequestDTO.getUserId());
        Apartment apartment = apartmentService.getApartmentById(saveRequestDTO.getApartmentId());

        Save save = Save.builder()
                .userId(user.getId())
                .apartment(apartment)
                .build();

        saveRepository.save(save);
        return modelMapper.map(save, SaveResponseDTO.class);

    }

    @Override
    public SaveResponseDTO getById(UUID saveID) {
        Save save = getSaveById(saveID);
        return modelMapper.map(save, SaveResponseDTO.class);
    }

    @Override
    public List<SaveResponseDTO> getUserSavedApartments(UUID userID, Integer page, Integer size) {
        userService.checkUserExists(userID);
        Pageable pageable = PageRequest.of(page, size);
        return saveRepository.findAllByUserIdOrderByCreatedDate(userID, pageable)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private void checkSaveUnique(UUID userID, UUID apartmentID) {
        if (saveRepository.existsByUserIdAndApartmentId(userID, apartmentID))
            throw new DuplicateDataException(
                    "Apartment has already saved with userID: " + userID + " and apartmentID: " + apartmentID);
    }

    private Save getSaveById(UUID saveID) {
        return saveRepository.findById(saveID)
                .orElseThrow(
                        () -> new DataNotFoundException("Save not found with ID: " + saveID)
                );
    }

    private SaveResponseDTO toDTO(Save save) {
        return modelMapper.map(save, SaveResponseDTO.class);
    }

}