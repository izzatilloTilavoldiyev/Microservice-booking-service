package uz.pdp.bookingservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveRequestDTO {
    @NotNull(message = "UserId must not be null") private UUID userId;
    @NotNull(message = "ApartmentId must not be null") private UUID apartmentId;
}