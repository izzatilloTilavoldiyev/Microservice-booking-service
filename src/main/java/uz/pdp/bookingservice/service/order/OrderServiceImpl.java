package uz.pdp.bookingservice.service.order;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.bookingservice.dto.request.OrderRequestDTO;
import uz.pdp.bookingservice.dto.response.OrderResponseDTO;
import uz.pdp.bookingservice.entity.Apartment;
import uz.pdp.bookingservice.entity.Order;
import uz.pdp.bookingservice.exception.DuplicateDataException;
import uz.pdp.bookingservice.repository.OrderRepository;
import uz.pdp.bookingservice.service.apartment.ApartmentServiceImpl;
import uz.pdp.bookingservice.service.user.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final UserService userService;

    private final ApartmentServiceImpl apartmentService;

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    @Override
    public OrderResponseDTO create(OrderRequestDTO orderRequestDTO) {
        checkOrderUnique(orderRequestDTO.getUserId(), orderRequestDTO.getApartmentId());
        userService.checkUserExists(orderRequestDTO.getUserId());
        Apartment apartment = apartmentService.getApartmentById(orderRequestDTO.getApartmentId());
        Order orderedApartment = modelMapper.map(orderRequestDTO, Order.class);
        orderedApartment.setApartment(apartment);
        orderRepository.save(orderedApartment);
        return modelMapper.map(orderedApartment, OrderResponseDTO.class);
    }

    private void checkOrderUnique(UUID userID, UUID apartmentID) {
        if (orderRepository.existsByUserIdAndApartmentId(userID, apartmentID))
            throw new DuplicateDataException(
                    "Apartment has already ordered with userID: " + userID + " apartmentID: " + apartmentID);
    }

}