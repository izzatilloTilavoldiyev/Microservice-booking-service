package uz.pdp.bookingservice.service.apartment;

import uz.pdp.bookingservice.dto.request.ApartmentCreateRequest;
import uz.pdp.bookingservice.dto.response.ApartmentResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ApartmentService {
    ApartmentCreateRequest save(ApartmentCreateRequest apartmentCreateRequest);

    ApartmentResponseDTO getById(UUID apartmentID);

    List<ApartmentResponseDTO> getAll(Integer page, Integer size);

    List<ApartmentResponseDTO> getAllDeleted(Integer page, Integer size);
}
