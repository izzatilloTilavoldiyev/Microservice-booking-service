package uz.pdp.bookingservice.service.apartment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.bookingservice.dto.request.ApartmentCreateRequest;
import uz.pdp.bookingservice.entity.Apartment;
import uz.pdp.bookingservice.entity.Attachment;
import uz.pdp.bookingservice.enums.ApartmentStatus;
import uz.pdp.bookingservice.repository.ApartmentRepository;
import uz.pdp.bookingservice.service.attachment.AttachmentService;

import static uz.pdp.bookingservice.mapper.ApartmentMapper.APARTMENT_MAPPER;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService{

    private final ApartmentRepository apartmentRepository;
    private final AttachmentService attachmentService;

    @Override
    public ApartmentCreateRequest save(ApartmentCreateRequest apartmentCreateRequest) {
        Apartment apartment = APARTMENT_MAPPER.toEntity(apartmentCreateRequest);
        Attachment attachment = attachmentService
                .getAttachmentById(apartmentCreateRequest.getAttachmentId());
        apartment.setAttachment(attachment);
        apartment.setStatus(ApartmentStatus.ACTIVE);
        Apartment savedApartment = apartmentRepository.save(apartment);
        ApartmentCreateRequest responseApartment = APARTMENT_MAPPER.toDto(savedApartment);
        responseApartment.setAttachmentId(savedApartment.getAttachment().getId());
        return responseApartment;
    }
}
