package uz.pdp.bookingservice.service.apartment;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.bookingservice.dto.request.ApartmentCreateRequest;
import uz.pdp.bookingservice.entity.Apartment;
import uz.pdp.bookingservice.entity.Attachment;
import uz.pdp.bookingservice.enums.ApartmentStatus;
import uz.pdp.bookingservice.repository.ApartmentRepository;
import uz.pdp.bookingservice.service.attachment.AttachmentService;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService{

    private final ApartmentRepository apartmentRepository;
    private final AttachmentService attachmentService;
    private final ModelMapper modelMapper;

    @Override
    public ApartmentCreateRequest save(ApartmentCreateRequest apartmentCreateRequest) {
        Apartment apartment = modelMapper.map(apartmentCreateRequest, Apartment.class);
        Attachment attachment = attachmentService
                .getAttachmentById(apartmentCreateRequest.getAttachmentId());
        apartment.setAttachment(attachment);
        apartment.setStatus(ApartmentStatus.ACTIVE);
        Apartment savedApartment = apartmentRepository.save(apartment);
        return modelMapper.map(savedApartment, ApartmentCreateRequest.class);
    }
}
