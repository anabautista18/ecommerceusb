package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.OrderItemRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderItemResponse;
import java.util.List;

public interface OrderItemService {
    List<OrderItemResponse> getAllOrderItems();
    OrderItemResponse getOrderItemById(Long id);
    OrderItemResponse save(OrderItemRequest orderItemRequest);
    OrderItemResponse update(Long id, OrderItemRequest orderItemRequest);
}
