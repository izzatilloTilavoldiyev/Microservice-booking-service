package uz.pdp.bookingservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @NotNull(message = "User id must not be null")
    private UUID userId;

    @NotNull(message = "Apartment id must not be null")
    private UUID apartmentId;

    @NotNull(message = "Total price must not be null")
    private Double totalPrice;

    @Schema(
            description = "Check-in date in format  yyyy-MM-dd",
            example = "2023-08-15"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Check-in date must not be null")
    private LocalDateTime checkInDate;

    @Schema(
            description = "Check-out date in format  yyyy-MM-dd",
            example = "2023-08-15"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Check-out date must not be null")
    private LocalDateTime checkOutDate;
}