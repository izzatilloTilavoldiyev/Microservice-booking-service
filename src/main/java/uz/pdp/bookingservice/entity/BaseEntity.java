package uz.pdp.bookingservice.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp created_date;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updated_date;
}
