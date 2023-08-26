package uz.pdp.bookingservice.service.apartment;

import uz.pdp.bookingservice.dto.request.ApartmentCreateRequest;
import uz.pdp.bookingservice.dto.response.ApartmentResponseDTO;
import uz.pdp.bookingservice.enums.ApartmentLevel;

import java.util.List;
import java.util.UUID;

public interface ApartmentService {
    ApartmentCreateRequest save(ApartmentCreateRequest apartmentCreateRequest);

    ApartmentResponseDTO getById(UUID apartmentID);

    List<ApartmentResponseDTO> getAll(Integer page, Integer size);

    List<ApartmentResponseDTO> getAllDeleted(Integer page, Integer size);

    List<ApartmentResponseDTO> getAllByLevel(Integer page, Integer size, ApartmentLevel level);

    List<ApartmentResponseDTO> getAllActive(Integer page, Integer size);

    List<ApartmentResponseDTO> getAllInactive(Integer page, Integer size);

    List<ApartmentResponseDTO> getAllBlocked(Integer page, Integer size);

    List<ApartmentResponseDTO> getAllBooked(Integer page, Integer size);

    void delete(UUID apartmentID);
}
