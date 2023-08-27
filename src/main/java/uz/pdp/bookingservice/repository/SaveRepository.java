package uz.pdp.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bookingservice.entity.Save;

import java.util.UUID;

public interface SaveRepository extends JpaRepository<Save, UUID> {
    boolean existsByUserIdAndApartmentId(UUID userId, UUID apartment_id);
}
