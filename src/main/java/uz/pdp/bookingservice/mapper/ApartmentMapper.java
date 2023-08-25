package uz.pdp.bookingservice.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.pdp.bookingservice.dto.request.ApartmentCreateRequest;
import uz.pdp.bookingservice.entity.Apartment;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApartmentMapper {

    ApartmentMapper APARTMENT_MAPPER = Mappers.getMapper(ApartmentMapper.class);

    Apartment toEntity(ApartmentCreateRequest apartmentCreateRequest);

    ApartmentCreateRequest toDto(Apartment apartment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Apartment partialUpdate(ApartmentCreateRequest apartmentCreateRequest, @MappingTarget Apartment apartment);

}
