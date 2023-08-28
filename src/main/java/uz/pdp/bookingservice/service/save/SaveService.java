package uz.pdp.bookingservice.service.save;

import uz.pdp.bookingservice.dto.request.SaveRequestDTO;
import uz.pdp.bookingservice.dto.response.SaveResponseDTO;

import java.util.List;
import java.util.UUID;

public interface SaveService {

    SaveResponseDTO addToSave(SaveRequestDTO saveRequestDTO);

    SaveResponseDTO getById(UUID saveID);

    List<SaveResponseDTO> getUserSavedApartments(UUID userID, Integer page, Integer size);
}
