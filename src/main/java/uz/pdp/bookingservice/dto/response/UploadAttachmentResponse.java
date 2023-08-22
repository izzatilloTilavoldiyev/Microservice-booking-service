package uz.pdp.bookingservice.dto.response;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UploadAttachmentResponse {
    private String originalName;
    private String fileType;
    private Long size;
    private String fileDownloadUri;
}
