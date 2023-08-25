package uz.pdp.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bookingservice.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
