package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.CartItemRequest;
import co.edu.usbcali.ecommerceusb.dto.CartItemResponse;
import co.edu.usbcali.ecommerceusb.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getAllCartItems() {
        return new ResponseEntity<>(cartItemService.getAllCartItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemResponse> getCartItemById(@PathVariable Long id) {
        return new ResponseEntity<>(cartItemService.getCartItemById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartItemResponse> createCartItem(@RequestBody CartItemRequest request) {
        return new ResponseEntity<>(cartItemService.save(request), HttpStatus.CREATED);
    }
}
