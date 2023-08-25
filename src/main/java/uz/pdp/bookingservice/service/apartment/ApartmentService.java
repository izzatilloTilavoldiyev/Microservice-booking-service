package uz.pdp.bookingservice.service.apartment;

import uz.pdp.bookingservice.dto.request.ApartmentCreateRequest;

public interface ApartmentService {
    ApartmentCreateRequest save(ApartmentCreateRequest apartmentCreateRequest);
}
