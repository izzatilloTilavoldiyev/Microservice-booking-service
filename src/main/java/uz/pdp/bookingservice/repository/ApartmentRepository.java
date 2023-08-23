package uz.pdp.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bookingservice.entity.Apartment;

import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {
}
