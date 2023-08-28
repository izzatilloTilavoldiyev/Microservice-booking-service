package uz.pdp.bookingservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bookingservice.entity.Save;

import java.util.List;
import java.util.UUID;

public interface SaveRepository extends JpaRepository<Save, UUID> {
    boolean existsByUserIdAndApartmentId(UUID userId, UUID apartment_id);

    List<Save> findAllByUserIdOrderByCreatedDate(UUID userId, Pageable pageable);
}
