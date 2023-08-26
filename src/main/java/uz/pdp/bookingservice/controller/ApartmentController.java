package uz.pdp.bookingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bookingservice.dto.request.ApartmentCreateRequest;
import uz.pdp.bookingservice.dto.response.ApartmentResponseDTO;
import uz.pdp.bookingservice.service.apartment.ApartmentService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/apartment")
@RequiredArgsConstructor
public class ApartmentController {

    /**
     * create
     *
     * get by id
     * get by id and level
     * get all page
     * get all deleted
     * get all by apartment level
     * get all active
     * get all inactive
     * get all blocked
     * get all booked
     *
     * update apartment
     * update status
     *
     * delete
     */

    private final ApartmentService apartmentService;

    @Operation(
            description = """
                    POST endpoint to create an apartment
                    201 = successfully created
                    400 = bad request
                    """,
            summary = "create apartment"
    )
    @PostMapping
    public ResponseEntity<ApartmentCreateRequest> create(
            @Valid @RequestBody ApartmentCreateRequest apartmentCreateRequest
    ) {
        ApartmentCreateRequest savedApartment = apartmentService.save(apartmentCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedApartment);
    }

    @Operation(
            description = "GET endpoint to get apartment by ID",
            summary = "get by ID"
    )
    @GetMapping("/{apartmentID}")
    public ResponseEntity<ApartmentResponseDTO> getById(
            @PathVariable UUID apartmentID
    ) {
        ApartmentResponseDTO apartmentResponseDTO = apartmentService.getById(apartmentID);
        return ResponseEntity.ok(apartmentResponseDTO);
    }
}
