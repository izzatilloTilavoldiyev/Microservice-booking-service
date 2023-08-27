package uz.pdp.bookingservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.bookingservice.dto.request.SaveRequestDTO;
import uz.pdp.bookingservice.dto.response.SaveResponseDTO;
import uz.pdp.bookingservice.service.save.SaveService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/save")
public class SaveController {

    /**
     * add to save
     * get by id
     * get user's saved apartments
     * get all saved apartments
     *
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

}