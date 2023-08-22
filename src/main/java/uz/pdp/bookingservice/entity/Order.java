package uz.pdp.bookingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity{

    @Column(nullable = false, columnDefinition = "uuid", name = "user_id")
    private UUID user;

    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Apartment apartment;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false, updatable = false)
    private LocalDateTime orderedDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime checkIdDate;

    @Column(nullable = false)
    private LocalDateTime checkOutDate;

}
