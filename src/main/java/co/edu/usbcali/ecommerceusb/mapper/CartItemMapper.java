package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.CartItemRequest;
import co.edu.usbcali.ecommerceusb.dto.CartItemResponse;
import co.edu.usbcali.ecommerceusb.model.CartItem;
import co.edu.usbcali.ecommerceusb.model.Cart;
import co.edu.usbcali.ecommerceusb.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartItemMapper {
    public static CartItemResponse toCartItemResponse(CartItem cartItem) {
        return CartItemResponse.builder()
                .id(cartItem.getId())
                .cartId(cartItem.getCart() != null ? cartItem.getCart().getId() : null)
                .productId(cartItem.getProduct() != null ? cartItem.getProduct().getId() : null)
                .quantity(cartItem.getQuantity())
                .build();
    }

    public static List<CartItemResponse> toCartItemResponseList(List<CartItem> cartItems) {
        List<CartItemResponse> responses = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            responses.add(toCartItemResponse(cartItem));
        }
        return responses;
    }

    public static CartItem toCartItem(CartItemRequest request, Cart cart, Product product) {
        return CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(request.getQuantity())
                .build();
    }
}
