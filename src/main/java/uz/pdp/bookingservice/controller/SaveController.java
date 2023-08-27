package uz.pdp.bookingservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bookingservice.dto.request.SaveRequestDTO;
import uz.pdp.bookingservice.dto.response.SaveResponseDTO;
import uz.pdp.bookingservice.service.save.SaveService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/save")
public class SaveController {

    /**
     * --- add to save
     *
     * get by id
     * get user's saved apartments
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

}