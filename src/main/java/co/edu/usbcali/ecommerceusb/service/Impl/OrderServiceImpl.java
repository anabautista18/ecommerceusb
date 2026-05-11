package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.OrderRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderResponse;
import co.edu.usbcali.ecommerceusb.mapper.OrderMapper;
import co.edu.usbcali.ecommerceusb.model.Order;
import co.edu.usbcali.ecommerceusb.model.User;
import co.edu.usbcali.ecommerceusb.repository.OrderRepository;
import co.edu.usbcali.ecommerceusb.repository.UserRepository;
import co.edu.usbcali.ecommerceusb.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public List<OrderResponse> getAllOrders() {
        return OrderMapper.toOrderResponseList(orderRepository.findAll());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        validateId(id, "orden");
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Orden no encontrada con el id: %d", id)));
        return OrderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse save(OrderRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de orden no puede ser nulo");
        }
        validateId(request.getUserId(), "usuario");
        if (request.getStatus() == null || request.getStatus().isBlank()) {
            throw new IllegalArgumentException("El estado no puede ser nulo ni vacio");
        }
        if (request.getTotalAmount() == null || request.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto total debe ser mayor que 0");
        }
        if (request.getCurrency() == null || request.getCurrency().isBlank()) {
            throw new IllegalArgumentException("La moneda no puede ser nula ni vacia");
        }
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException(String.format("Usuario no encontrado con el id: %d", request.getUserId())));
        return OrderMapper.toOrderResponse(orderRepository.save(OrderMapper.toOrder(request, user)));
    }

    @Override
    public OrderResponse update(Long id, OrderRequest request) {
        validateId(id, "orden");
        if (request == null) {
            throw new IllegalArgumentException("El request de orden no puede ser nulo");
        }
        validateId(request.getUserId(), "usuario");
        if (request.getStatus() == null || request.getStatus().isBlank()) {
            throw new IllegalArgumentException("El estado no puede ser nulo ni vacio");
        }
        if (request.getTotalAmount() == null || request.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto total debe ser mayor que 0");
        }
        if (request.getCurrency() == null || request.getCurrency().isBlank()) {
            throw new IllegalArgumentException("La moneda no puede ser nula ni vacia");
        }
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Orden no encontrada con el id: %d", id)));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException(String.format("Usuario no encontrado con el id: %d", request.getUserId())));
        existingOrder.setUser(user);
        existingOrder.setStatus(request.getStatus());
        existingOrder.setTotalAmount(request.getTotalAmount());
        existingOrder.setCurrency(request.getCurrency());
        return OrderMapper.toOrderResponse(orderRepository.save(existingOrder));
    }

    private void validateId(Long id, String name) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Debe ingresar un id valido para " + name);
        }
    }
}
