package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.InventoryMovementRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryMovementResponse;
import java.util.List;

public interface InventoryMovementService {
    List<InventoryMovementResponse> getAllInventoryMovements();
    InventoryMovementResponse getInventoryMovementById(Long id);
    InventoryMovementResponse save(InventoryMovementRequest inventoryMovementRequest);
}
