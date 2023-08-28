package uz.pdp.bookingservice.service.order;

import uz.pdp.bookingservice.dto.request.OrderRequestDTO;
import uz.pdp.bookingservice.dto.response.OrderResponseDTO;

public interface OrderService {

    OrderResponseDTO create(OrderRequestDTO orderRequestDTO);
}
