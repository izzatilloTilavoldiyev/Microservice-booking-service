package uz.pdp.bookingservice.service.apartment;

import uz.pdp.bookingservice.dto.request.ApartmentCreateRequest;
import uz.pdp.bookingservice.dto.response.ApartmentResponseDTO;

import java.util.UUID;

public interface ApartmentService {
    ApartmentCreateRequest save(ApartmentCreateRequest apartmentCreateRequest);

    ApartmentResponseDTO getById(UUID apartmentID);
}
