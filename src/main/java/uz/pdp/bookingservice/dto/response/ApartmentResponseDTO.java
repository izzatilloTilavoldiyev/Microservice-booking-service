package uz.pdp.bookingservice.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.bookingservice.enums.ApartmentLevel;

import java.time.LocalDateTime;
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApartmentResponseDTO {
    private String title;

    private String description;

    private Double price;

    private String priceDescription;

    private Integer roomCount;

    private Integer size;

    private String amenities;

    private AttachmentResponseDTO attachment;

    private ApartmentLevel level;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
