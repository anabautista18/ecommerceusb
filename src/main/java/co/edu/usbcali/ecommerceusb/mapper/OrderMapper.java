package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.OrderRequest;
import co.edu.usbcali.ecommerceusb.dto.OrderResponse;
import co.edu.usbcali.ecommerceusb.model.Order;
import co.edu.usbcali.ecommerceusb.model.User;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser() != null ? order.getUser().getId() : null)
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .currency(order.getCurrency())
                .build();
    }

    public static List<OrderResponse> toOrderResponseList(List<Order> orders) {
        List<OrderResponse> responses = new ArrayList<>();
        for (Order order : orders) {
            responses.add(toOrderResponse(order));
        }
        return responses;
    }

    public static Order toOrder(OrderRequest request, User user) {
        return Order.builder()
                .user(user)
                .status(request.getStatus())
                .totalAmount(request.getTotalAmount())
                .currency(request.getCurrency())
                .build();
    }
}
