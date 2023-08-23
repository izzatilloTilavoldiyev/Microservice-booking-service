package uz.pdp.bookingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.bookingservice.dto.response.UploadAttachmentResponse;
import uz.pdp.bookingservice.service.attachment.AttachmentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    /**
     *
     * @param file -> Multipart request file which contains the input send from user
     * @return UploadFileResponse which includes download url and file name
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadAttachmentResponse uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        return attachmentService.saveFile(file);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Path file = attachmentService.downloadFile(fileName);
        try {
            String mediaType = Files.probeContentType(file);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mediaType))
                    .body(new UrlResource(file.toUri()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}