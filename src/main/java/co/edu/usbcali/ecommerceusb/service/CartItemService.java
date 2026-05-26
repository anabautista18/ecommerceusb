package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CartItemRequest;
import co.edu.usbcali.ecommerceusb.dto.CartItemResponse;
import java.util.List;

public interface CartItemService {
    List<CartItemResponse> getAllCartItems();
    CartItemResponse getCartItemById(Long id);
    CartItemResponse save(CartItemRequest cartItemRequest);
    CartItemResponse update(Long id, CartItemRequest cartItemRequest);
    void deleteById(Long id);
}
