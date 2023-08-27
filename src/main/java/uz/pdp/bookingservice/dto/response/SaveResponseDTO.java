package uz.pdp.bookingservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveResponseDTO {

    private UUID userId;

    private ApartmentResponseDTO apartment;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}