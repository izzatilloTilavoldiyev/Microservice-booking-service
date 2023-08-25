package uz.pdp.bookingservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentCreateRequest {
    @NotBlank(message = "title must not be blank") String title;
    String description;
    @NotNull(message = "price must not be null") Double price;
    String priceDescription;
    @NotNull(message = "room count must not be null") Integer roomCount;
    @NotNull(message = "size must not be null") Integer size;
    @NotBlank(message = "amenities must not be blank") String amenities;
    @NotNull(message = "attachment must not be null") UUID attachmentId;
    @NotBlank(message = "level must not be blank") String level;
}
