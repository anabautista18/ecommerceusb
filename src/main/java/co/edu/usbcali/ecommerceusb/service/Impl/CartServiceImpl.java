package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CartRequest;
import co.edu.usbcali.ecommerceusb.dto.CartResponse;
import co.edu.usbcali.ecommerceusb.mapper.CartMapper;
import co.edu.usbcali.ecommerceusb.model.Cart;
import co.edu.usbcali.ecommerceusb.model.User;
import co.edu.usbcali.ecommerceusb.repository.CartRepository;
import co.edu.usbcali.ecommerceusb.repository.UserRepository;
import co.edu.usbcali.ecommerceusb.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public List<CartResponse> getAllCarts() {
        return CartMapper.toCartResponseList(cartRepository.findAll());
    }

    @Override
    public CartResponse getCartById(Long id) {
        validateId(id, "carrito");
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Carrito no encontrado con el id: %d", id)));
        return CartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse save(CartRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de carrito no puede ser nulo");
        }
        validateId(request.getUserId(), "usuario");
        if (request.getStatus() == null || request.getStatus().isBlank()) {
            throw new IllegalArgumentException("El estado no puede ser nulo ni vacio");
        }
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException(String.format("Usuario no encontrado con el id: %d", request.getUserId())));
        return CartMapper.toCartResponse(cartRepository.save(CartMapper.toCart(request, user)));
    }

    @Override
    public CartResponse update(Long id, CartRequest request) {
        validateId(id, "carrito");
        if (request == null) {
            throw new IllegalArgumentException("El request de carrito no puede ser nulo");
        }
        validateId(request.getUserId(), "usuario");
        if (request.getStatus() == null || request.getStatus().isBlank()) {
            throw new IllegalArgumentException("El estado no puede ser nulo ni vacio");
        }
        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Carrito no encontrado con el id: %d", id)));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException(String.format("Usuario no encontrado con el id: %d", request.getUserId())));
        existingCart.setUser(user);
        existingCart.setStatus(request.getStatus());
        return CartMapper.toCartResponse(cartRepository.save(existingCart));
    }

    @Override
    public void deleteById(Long id) {
        validateId(id, "carrito");
        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Carrito no encontrado con el id: %d", id)));
        cartRepository.delete(existingCart);
    }

    private void validateId(Long id, String name) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Debe ingresar un id valido para " + name);
        }
    }
}
