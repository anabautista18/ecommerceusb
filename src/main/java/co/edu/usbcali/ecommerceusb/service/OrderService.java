package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.OrderRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderResponse;
import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
    OrderResponse save(OrderRequest orderRequest);
    OrderResponse update(Long id, OrderRequest orderRequest);
    void deleteById(Long id);
}
