package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.OrderItemRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderItemResponse;
import co.edu.usbcali.ecommerceusb.model.OrderItem;
import co.edu.usbcali.ecommerceusb.model.Order;
import co.edu.usbcali.ecommerceusb.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderItemMapper {
    public static OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrder() != null ? orderItem.getOrder().getId() : null)
                .productId(orderItem.getProduct() != null ? orderItem.getProduct().getId() : null)
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .build();
    }

    public static List<OrderItemResponse> toOrderItemResponseList(List<OrderItem> orderItems) {
        List<OrderItemResponse> responses = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            responses.add(toOrderItemResponse(orderItem));
        }
        return responses;
    }

    public static OrderItem toOrderItem(OrderItemRequest request, Order order, Product product) {
        return OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .build();
    }
}
