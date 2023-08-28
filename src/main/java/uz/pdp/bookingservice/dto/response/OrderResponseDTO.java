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
public class OrderResponseDTO {
    private UUID id;
    private UUID user;
    private ApartmentResponseDTO apartment;
    private Double totalPrice;
    private LocalDateTime orderedDate;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}