package uz.pdp.bookingservice.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.bookingservice.enums.ApartmentLevel;
import uz.pdp.bookingservice.enums.ApartmentStatus;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Apartment extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private Double price;

    private String priceDescription;

    @Column(nullable = false)
    private Integer roomCount;

    @Column(nullable = false)
    private Integer size;

    @Column(nullable = false)
    private String amenities;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Attachment attachment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApartmentStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApartmentLevel level;

    private boolean deleted;
}
