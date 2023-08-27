package uz.pdp.bookingservice.service.save;

import uz.pdp.bookingservice.dto.request.SaveRequestDTO;
import uz.pdp.bookingservice.dto.response.SaveResponseDTO;

import java.util.UUID;

public interface SaveService {

    SaveResponseDTO addToSave(SaveRequestDTO saveRequestDTO);

    SaveResponseDTO getById(UUID saveID);
}
