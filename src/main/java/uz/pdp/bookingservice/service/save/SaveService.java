package uz.pdp.bookingservice.service.save;

import uz.pdp.bookingservice.dto.request.SaveRequestDTO;
import uz.pdp.bookingservice.dto.response.SaveResponseDTO;

public interface SaveService {

    SaveResponseDTO addToSave(SaveRequestDTO saveRequestDTO);
}
