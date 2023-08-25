    package uz.pdp.bookingservice.controller;

    import io.swagger.v3.oas.annotations.Operation;
    import lombok.RequiredArgsConstructor;
    import org.springframework.core.io.Resource;
    import org.springframework.core.io.UrlResource;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import uz.pdp.bookingservice.entity.Attachment;
    import uz.pdp.bookingservice.service.attachment.AttachmentService;

    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.UUID;

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
        @Operation(
                description = "POST endpoint to upload any files. Max file size must be 300MB",
                summary = "API to store file"
        )
        @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public Attachment uploadFile(
                @RequestParam("file") MultipartFile file
        ) {
            return attachmentService.saveFile(file);
        }

//        @Operation(
//                description = "GET endpoint to download file. You need to give original file name",
//                summary = "API to download file"
//        )
//        @GetMapping("/download/{fileName}")
//        public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
//            Path file = attachmentService.downloadFile(fileName);
//            try {
//                String mediaType = Files.probeContentType(file);
//                return ResponseEntity.ok()
//                        .contentType(MediaType.parseMediaType(mediaType))
//                        .body(new UrlResource(file.toUri()));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }

        @Operation(
                description = "GET endpoint to download file. You need to give file id. It will send you file PDF",
                summary = "API to download file"
        )
        @GetMapping("/download/{fileId}")
        public ResponseEntity<Resource> downloadFile(@PathVariable UUID fileId) {
            Path file = attachmentService.downloadFile(fileId);
            try {
                Resource resource = new UrlResource(file.toUri());

                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
