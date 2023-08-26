package uz.pdp.bookingservice.service.apartment;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.pdp.bookingservice.dto.request.ApartmentCreateRequest;
import uz.pdp.bookingservice.dto.response.ApartmentResponseDTO;
import uz.pdp.bookingservice.entity.Apartment;
import uz.pdp.bookingservice.entity.Attachment;
import uz.pdp.bookingservice.enums.ApartmentLevel;
import uz.pdp.bookingservice.enums.ApartmentStatus;
import uz.pdp.bookingservice.exception.BadRequestException;
import uz.pdp.bookingservice.exception.DataNotFoundException;
import uz.pdp.bookingservice.repository.ApartmentRepository;
import uz.pdp.bookingservice.service.attachment.AttachmentService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final AttachmentService attachmentService;
    private final ModelMapper modelMapper;

    @Override
    public ApartmentCreateRequest save(ApartmentCreateRequest apartmentCreateRequest) {
        Apartment apartment = modelMapper.map(apartmentCreateRequest, Apartment.class);
        if (apartment.getLevel() == null)
            throw new BadRequestException("Apartment level: " + apartmentCreateRequest.getLevel() + " not supported");
        Attachment attachment = attachmentService
                .getAttachmentById(apartmentCreateRequest.getAttachmentId());
        apartment.setAttachment(attachment);
        apartment.setStatus(ApartmentStatus.ACTIVE);
        Apartment savedApartment = apartmentRepository.save(apartment);
        return modelMapper.map(savedApartment, ApartmentCreateRequest.class);
    }

    @Override
    public ApartmentResponseDTO getById(UUID apartmentID) {
        Apartment apartment = getApartmentById(apartmentID);
        return modelMapper.map(apartment, ApartmentResponseDTO.class);
    }

    @Override
    public List<ApartmentResponseDTO> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return apartmentRepository.findAllApartments(pageable)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<ApartmentResponseDTO> getAllDeleted(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return apartmentRepository.findAllDeletedApartments(pageable)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<ApartmentResponseDTO> getAllByLevel(Integer page, Integer size, ApartmentLevel level) {
        Pageable pageable = PageRequest.of(page, size);
        return apartmentRepository.findAllByLevel(pageable, level)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<ApartmentResponseDTO> getAllActive(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return apartmentRepository.findAllActive(pageable)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<ApartmentResponseDTO> getAllInactive(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return apartmentRepository.findAllInactive(pageable)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<ApartmentResponseDTO> getAllBlocked(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return apartmentRepository.findAllBlocked(pageable)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<ApartmentResponseDTO> getAllBooked(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return apartmentRepository.findAllBooked(pageable)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void delete(UUID apartmentID) {
        if(!apartmentRepository.existsApartmentById(apartmentID))
            throw new DataNotFoundException("Apartment not found with id: " + apartmentID);
        apartmentRepository.deleteApartmentById(apartmentID);
    }

    private Apartment getApartmentById(UUID apartmentID) {
        return apartmentRepository.findApartmentById(apartmentID)
                .orElseThrow(
                        () -> new DataNotFoundException("Apartment not found with id: " + apartmentID)
                );
    }

    private ApartmentResponseDTO toDTO(Apartment apartment) {
        return modelMapper.map(apartment, ApartmentResponseDTO.class);
    }
}
