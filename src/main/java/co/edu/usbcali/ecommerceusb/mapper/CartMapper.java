package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.CartRequest;
import co.edu.usbcali.ecommerceusb.dto.CartResponse;
import co.edu.usbcali.ecommerceusb.model.Cart;
import co.edu.usbcali.ecommerceusb.model.User;

import java.util.ArrayList;
import java.util.List;

public class CartMapper {
    public static CartResponse toCartResponse(Cart cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUser() != null ? cart.getUser().getId() : null)
                .status(cart.getStatus())
                .build();
    }

    public static List<CartResponse> toCartResponseList(List<Cart> carts) {
        List<CartResponse> responses = new ArrayList<>();
        for (Cart cart : carts) {
            responses.add(toCartResponse(cart));
        }
        return responses;
    }

    public static Cart toCart(CartRequest request, User user) {
        return Cart.builder()
                .user(user)
                .status(request.getStatus())
                .build();
    }
}
