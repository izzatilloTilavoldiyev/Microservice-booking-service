package uz.pdp.bookingservice.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApartmentUpdateUserDTO {
    private String title;

    private String description;

    private Double price;

    private String priceDescription;

    private Integer roomCount;

    private Integer size;

    private String amenities;
}
