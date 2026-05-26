package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CartRequest;
import co.edu.usbcali.ecommerceusb.dto.CartResponse;
import java.util.List;

public interface CartService {
    List<CartResponse> getAllCarts();
    CartResponse getCartById(Long id);
    CartResponse save(CartRequest cartRequest);
    CartResponse update(Long id, CartRequest cartRequest);
    void deleteById(Long id);
}
