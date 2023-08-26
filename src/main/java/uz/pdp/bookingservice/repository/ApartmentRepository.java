package uz.pdp.bookingservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.bookingservice.entity.Apartment;
import uz.pdp.bookingservice.enums.ApartmentLevel;

import java.util.Optional;
import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {

    @Query("from apartment a where a.id = :apartmentId " +
            "and a.status = 'ACTIVE' and not a.deleted")
    Optional<Apartment> findApartmentById(UUID apartmentId);

    @Query("from apartment a where not a.deleted order by a.createdDate desc")
    Page<Apartment> findAllApartments(Pageable pageable);

    @Query("from apartment a where a.deleted = true order by a.createdDate desc")
    Page<Apartment> findAllDeletedApartments(Pageable pageable);

    @Query("from apartment a where a.status = 'ACTIVE' " +
            "and a.level = :level and not a.deleted order by a.createdDate desc")
    Page<Apartment> findAllByLevel(Pageable pageable, ApartmentLevel level);

    @Query("from apartment a where a.status = 'ACTIVE' and not a.deleted order by a.createdDate desc")
    Page<Apartment> findAllActive(Pageable pageable);

    @Query("from apartment a where a.status = 'INACTIVE' and not a.deleted order by a.createdDate desc")
    Page<Apartment> findAllInactive(Pageable pageable);

    @Query("from apartment a where a.status = 'BLOCKED' and not a.deleted order by a.createdDate desc")
    Page<Apartment> findAllBlocked(Pageable pageable);

    @Query("from apartment a where a.status = 'BOOKED' and not a.deleted order by a.createdDate desc")
    Page<Apartment> findAllBooked(Pageable pageable);

}
