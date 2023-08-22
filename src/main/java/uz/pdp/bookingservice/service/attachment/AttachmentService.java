package uz.pdp.bookingservice.service.attachment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.bookingservice.dto.response.UploadAttachmentResponse;
import uz.pdp.bookingservice.entity.Attachment;
import uz.pdp.bookingservice.repository.AttachmentRepository;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

import static uz.pdp.bookingservice.mapper.AttachmentMapper.ATTACHMENT_MAPPER;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final Path fileLocation;

    @Autowired
    private AttachmentRepository attachmentRepository;

    public AttachmentService() {
        String fileUploadDir = "C:\\JAVA\\Microservice_Projects\\booking-service\\src\\main\\resources\\uploads";
        this.fileLocation = Paths.get(fileUploadDir)
                .toAbsolutePath().normalize();
    }

    public UploadAttachmentResponse saveFile(MultipartFile file) {
        String fullFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Path targetLocation = fileLocation.resolve(fullFileName);
            Files.copy(file.getInputStream(), targetLocation);
        } catch (FileAlreadyExistsException e) {
            String[] fileNameAndType = fullFileName.split("\\.");
            fullFileName = fileNameAndType[0] + System.currentTimeMillis() + "." + fileNameAndType[1];
            Path targetLocation = fileLocation.resolve(fullFileName);
            try {
                Files.copy(file.getInputStream(), targetLocation);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Attachment attachment = Attachment.builder()
                .originalName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .fileDownloadUri(fileLocation + "\\" + file.getOriginalFilename())
                .size(file.getSize())
                .build();
        attachmentRepository.save(attachment);

        return UploadAttachmentResponse.builder()
                .originalName(attachment.getOriginalName())
                .fileType(attachment.getFileType())
                .fileDownloadUri(attachment.getFileDownloadUri())
                .size(attachment.getSize())
                .build();
    }


    public Path downloadFile(String fileName) {
        return fileLocation.resolve(fileName);
    }
}
