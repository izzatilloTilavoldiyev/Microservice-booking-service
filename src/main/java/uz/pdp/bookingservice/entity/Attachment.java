package uz.pdp.bookingservice.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "attachments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Attachment extends BaseEntity {
    private String originalName;
    private String extension;
    private String fileType;
    private Long size;
    private String fileDownloadUri;
}
