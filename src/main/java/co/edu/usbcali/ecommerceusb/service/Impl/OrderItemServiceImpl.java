package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.OrderItemRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderItemResponse;
import co.edu.usbcali.ecommerceusb.mapper.OrderItemMapper;
import co.edu.usbcali.ecommerceusb.model.Order;
import co.edu.usbcali.ecommerceusb.model.OrderItem;
import co.edu.usbcali.ecommerceusb.model.Product;
import co.edu.usbcali.ecommerceusb.repository.OrderItemRepository;
import co.edu.usbcali.ecommerceusb.repository.OrderRepository;
import co.edu.usbcali.ecommerceusb.repository.ProductRepository;
import co.edu.usbcali.ecommerceusb.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderItemResponse> getAllOrderItems() {
        return OrderItemMapper.toOrderItemResponseList(orderItemRepository.findAll());
    }

    @Override
    public OrderItemResponse getOrderItemById(Long id) {
        validateId(id, "item de orden");
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Item de orden no encontrado con el id: %d", id)));
        return OrderItemMapper.toOrderItemResponse(orderItem);
    }

    @Override
    public OrderItemResponse save(OrderItemRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de item de orden no puede ser nulo");
        }
        validateId(request.getOrderId(), "orden");
        validateId(request.getProductId(), "producto");
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        if (request.getUnitPrice() == null || request.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser mayor que 0");
        }
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException(String.format("Orden no encontrada con el id: %d", request.getOrderId())));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException(String.format("Producto no encontrado con el id: %d", request.getProductId())));
        return OrderItemMapper.toOrderItemResponse(orderItemRepository.save(OrderItemMapper.toOrderItem(request, order, product)));
    }

    private void validateId(Long id, String name) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Debe ingresar un id valido para " + name);
        }
    }
}
