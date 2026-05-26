package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CartItemRequest;
import co.edu.usbcali.ecommerceusb.dto.CartItemResponse;
import co.edu.usbcali.ecommerceusb.mapper.CartItemMapper;
import co.edu.usbcali.ecommerceusb.model.Cart;
import co.edu.usbcali.ecommerceusb.model.CartItem;
import co.edu.usbcali.ecommerceusb.model.Product;
import co.edu.usbcali.ecommerceusb.repository.CartItemRepository;
import co.edu.usbcali.ecommerceusb.repository.CartRepository;
import co.edu.usbcali.ecommerceusb.repository.ProductRepository;
import co.edu.usbcali.ecommerceusb.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public List<CartItemResponse> getAllCartItems() {
        return CartItemMapper.toCartItemResponseList(cartItemRepository.findAll());
    }

    @Override
    public CartItemResponse getCartItemById(Long id) {
        validateId(id, "item de carrito");
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Item de carrito no encontrado con el id: %d", id)));
        return CartItemMapper.toCartItemResponse(cartItem);
    }

    @Override
    public CartItemResponse save(CartItemRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de item de carrito no puede ser nulo");
        }
        validateId(request.getCartId(), "carrito");
        validateId(request.getProductId(), "producto");
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new RuntimeException(String.format("Carrito no encontrado con el id: %d", request.getCartId())));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException(String.format("Producto no encontrado con el id: %d", request.getProductId())));
        return CartItemMapper.toCartItemResponse(cartItemRepository.save(CartItemMapper.toCartItem(request, cart, product)));
    }

    @Override
    public CartItemResponse update(Long id, CartItemRequest request) {
        validateId(id, "item de carrito");
        if (request == null) {
            throw new IllegalArgumentException("El request de item de carrito no puede ser nulo");
        }
        validateId(request.getCartId(), "carrito");
        validateId(request.getProductId(), "producto");
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        CartItem existingCartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Item de carrito no encontrado con el id: %d", id)));
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new RuntimeException(String.format("Carrito no encontrado con el id: %d", request.getCartId())));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException(String.format("Producto no encontrado con el id: %d", request.getProductId())));
        existingCartItem.setCart(cart);
        existingCartItem.setProduct(product);
        existingCartItem.setQuantity(request.getQuantity());
        return CartItemMapper.toCartItemResponse(cartItemRepository.save(existingCartItem));
    }

    @Override
    public void deleteById(Long id) {
        validateId(id, "item de carrito");
        CartItem existingCartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Item de carrito no encontrado con el id: %d", id)));
        cartItemRepository.delete(existingCartItem);
    }

    private void validateId(Long id, String name) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Debe ingresar un id valido para " + name);
        }
    }
}
