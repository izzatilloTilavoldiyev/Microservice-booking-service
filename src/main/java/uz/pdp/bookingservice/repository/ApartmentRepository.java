package uz.pdp.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.bookingservice.entity.Apartment;

import java.util.Optional;
import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {

    @Query("from apartment a where a.id = :apartmentId and a.status = 'ACTIVE'")
    Optional<Apartment> findApartmentByIdAndDeletedFalse(UUID apartmentId);
}
