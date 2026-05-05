package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.InventoryMovementRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryMovementResponse;
import co.edu.usbcali.ecommerceusb.service.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-movements")
@RequiredArgsConstructor
public class InventoryMovementController {
    private final InventoryMovementService inventoryMovementService;

    @GetMapping
    public ResponseEntity<List<InventoryMovementResponse>> getAllInventoryMovements() {
        return new ResponseEntity<>(inventoryMovementService.getAllInventoryMovements(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryMovementResponse> getInventoryMovementById(@PathVariable Long id) {
        return new ResponseEntity<>(inventoryMovementService.getInventoryMovementById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InventoryMovementResponse> createInventoryMovement(@RequestBody InventoryMovementRequest request) {
        return new ResponseEntity<>(inventoryMovementService.save(request), HttpStatus.CREATED);
    }
}
