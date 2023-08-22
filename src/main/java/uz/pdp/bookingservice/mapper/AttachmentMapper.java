package uz.pdp.bookingservice.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.pdp.bookingservice.dto.response.UploadAttachmentResponse;
import uz.pdp.bookingservice.entity.Attachment;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AttachmentMapper {

    AttachmentMapper ATTACHMENT_MAPPER = Mappers.getMapper(AttachmentMapper.class);

    Attachment toEntity(UploadAttachmentResponse uploadAttachmentResponse);

    UploadAttachmentResponse toDto(Attachment attachment);

}
