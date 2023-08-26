package uz.pdp.bookingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AttachmentResponseDTO {
    private UUID id;
    private String originalName;
    private String fileDownloadUri;
}