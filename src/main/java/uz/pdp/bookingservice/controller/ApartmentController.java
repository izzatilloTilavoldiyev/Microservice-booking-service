package uz.pdp.bookingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bookingservice.dto.request.ApartmentCreateRequest;
import uz.pdp.bookingservice.dto.response.ApartmentResponseDTO;
import uz.pdp.bookingservice.enums.ApartmentLevel;
import uz.pdp.bookingservice.service.apartment.ApartmentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/apartment")
@RequiredArgsConstructor
public class ApartmentController {

    /**
     * --- create
     *
     * --- get by id
     * --- get all page
     * --- get all deleted
     * --- get all by apartment level
     * --- get all active
     * --- get all inactive
     * --- get all blocked
     * --- get all booked
     *
     * update apartment
     * update status
     *
     * --- delete
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

    @Operation(
            description = "GET endpoint to get all apartments page",
            summary = "get all"
    )
    @GetMapping("/all")
    public ResponseEntity<List<ApartmentResponseDTO>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        List<ApartmentResponseDTO> allApartments = apartmentService.getAll(page, size);
        return ResponseEntity.ok(allApartments);
    }

    @Operation(
            description = "GET endpoint to get all deleted apartments page",
            summary = "get all deleted"
    )
    @GetMapping("/all-deleted")
    public ResponseEntity<List<ApartmentResponseDTO>> getAllDeleted(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        List<ApartmentResponseDTO> allDeletedApartments = apartmentService.getAllDeleted(page, size);
        return ResponseEntity.ok(allDeletedApartments);
    }

    @Operation(
            description = "GET endpoint to get all apartments page by Level",
            summary = "get all by level"
    )
    @GetMapping("/all-by-level")
    public ResponseEntity<List<ApartmentResponseDTO>> getAllByLevel(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam ApartmentLevel level
    ) {
        List<ApartmentResponseDTO> allApartments = apartmentService.getAllByLevel(page, size, level);
        return ResponseEntity.ok(allApartments);
    }

    @Operation(
            description = "GET endpoint to get all active apartments",
            summary = "get all active"
    )
    @GetMapping("/all-active")
    public ResponseEntity<List<ApartmentResponseDTO>> getAllActive(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        List<ApartmentResponseDTO> activeApartments = apartmentService.getAllActive(page, size);
        return ResponseEntity.ok(activeApartments);
    }

    @Operation(
            description = "GET endpoint to get all inactive apartments",
            summary = "get all inactive"
    )
    @GetMapping("/all-inactive")
    public ResponseEntity<List<ApartmentResponseDTO>> getAllInactive(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        List<ApartmentResponseDTO> inactiveApartments = apartmentService.getAllInactive(page, size);
        return ResponseEntity.ok(inactiveApartments);
    }

    @Operation(
            description = "GET endpoint to get all blocked apartments",
            summary = "get all blocked"
    )
    @GetMapping("/all-blocked")
    public ResponseEntity<List<ApartmentResponseDTO>> getAllBlocked(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        List<ApartmentResponseDTO> blockedApartments = apartmentService.getAllBlocked(page, size);
        return ResponseEntity.ok(blockedApartments);
    }

    @Operation(
            description = "GET endpoint to get all booked apartments",
            summary = "get all booked"
    )
    @GetMapping("/all-booked")
    public ResponseEntity<List<ApartmentResponseDTO>> getAllBooked(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        List<ApartmentResponseDTO> bookedApartments = apartmentService.getAllBooked(page, size);
        return ResponseEntity.ok(bookedApartments);
    }

    @Operation(
            description = "DELETE endpoint to delete apartment by id",
            summary = "delete"
    )
    @DeleteMapping("/{apartmentID}")
    public ResponseEntity<String> delete(
            @PathVariable UUID apartmentID
    ) {
        apartmentService.delete(apartmentID);
        return ResponseEntity.ok("Success");
    }

}
