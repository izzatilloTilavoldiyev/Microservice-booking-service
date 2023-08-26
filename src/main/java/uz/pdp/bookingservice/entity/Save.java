package uz.pdp.bookingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "save")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Save extends BaseEntity{

    @Column(nullable = false, columnDefinition = "uuid")
    private UUID userId;

    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Apartment apartment;
}
