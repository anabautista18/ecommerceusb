package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.CartRequest;
import co.edu.usbcali.ecommerceusb.dto.CartResponse;
import co.edu.usbcali.ecommerceusb.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartResponse>> getAllCarts() {
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@RequestBody CartRequest request) {
        return new ResponseEntity<>(cartService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResponse> updateCart(@PathVariable Long id, @RequestBody CartRequest request) {
        return new ResponseEntity<>(cartService.update(id, request), HttpStatus.OK);
    }



        @DeleteMapping("/{id}")
        public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {

        cartService.deleteById(id);

        return new ResponseEntity<>(
            "Registro eliminado correctamente",
            HttpStatus.OK
        );
        }

}



