package uz.pdp.bookingservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bookingservice.dto.request.SaveRequestDTO;
import uz.pdp.bookingservice.dto.response.SaveResponseDTO;
import uz.pdp.bookingservice.service.save.SaveService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/save")
public class SaveController {

    /**
     * --- add to save
     *
     * --- get by id
     * --- get user's saved apartments
     * get all saved apartments
     *
     * delete
     */

    private final SaveService saveService;

    @Operation(
            description = "POST endpoint to save apartment to saved branch",
            summary = "save apartment"
    )
    @PostMapping
    public ResponseEntity<SaveResponseDTO> addToSave(
            @Valid @RequestBody SaveRequestDTO saveRequestDTO
    ) {
        SaveResponseDTO savedApartment = saveService.addToSave(saveRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedApartment);
    }

    @Operation(
            description = "GET endpoint to get saved apartment by ID",
            summary = "get by id"
    )
    @GetMapping("/{saveID}")
    public ResponseEntity<SaveResponseDTO> getById(
            @PathVariable UUID saveID
    ) {
        SaveResponseDTO saveResponseDTO = saveService.getById(saveID);
        return ResponseEntity.ok(saveResponseDTO);
    }

    @Operation(
            description = "GET endpoint to get all user's saved apartments",
            summary = "all user's saved apartments"
    )
    @GetMapping("/all-user-saved/{userID}")
    public ResponseEntity<List<SaveResponseDTO>> getUserSavedApartments(
            @PathVariable UUID userID,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        List<SaveResponseDTO> userSavedApartments = saveService.getUserSavedApartments(userID, page, size);
        return ResponseEntity.ok(userSavedApartments);
    }

    @Operation(
            description = "GET endpoint to get all saved apartments",
            summary = "get all"
    )
    @GetMapping("/all")
    public ResponseEntity<List<SaveResponseDTO>> getAllSavedApartments(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        List<SaveResponseDTO> allSavedApartments = saveService.getAllSavedApartments(page, size);
        return ResponseEntity.ok(allSavedApartments);
    }

    @Operation(
            description = "DELETE endpoint to delete save by saveID",
            summary = "delete"
    )
    @DeleteMapping("/{saveID}")
    public ResponseEntity<String> delete(
            @PathVariable UUID saveID
    ) {
        saveService.delete(saveID);
        return ResponseEntity.ok("Successfully deleted");
    }

}