package uz.pdp.bookingservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.bookingservice.dto.request.ApartmentCreateRequest;
import uz.pdp.bookingservice.service.apartment.ApartmentService;

@RestController
@RequestMapping("/api/v1/apartment")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;

    @PostMapping
    public ResponseEntity<ApartmentCreateRequest> create(
            @Valid @RequestBody ApartmentCreateRequest apartmentCreateRequest
    ) {
        ApartmentCreateRequest savedApartment = apartmentService.save(apartmentCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedApartment);
    }
}
