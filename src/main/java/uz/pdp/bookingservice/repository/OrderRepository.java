package uz.pdp.bookingservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bookingservice.entity.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    boolean existsByUserIdAndApartmentId(UUID userId, UUID apartment_id);
}