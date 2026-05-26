package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.InventoryRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryResponse;
import java.util.List;

public interface InventoryService {
    List<InventoryResponse> getAllInventories();
    InventoryResponse getInventoryById(Long id);
    InventoryResponse save(InventoryRequest inventoryRequest);
    InventoryResponse update(Long id, InventoryRequest inventoryRequest);
    void deleteById(Long id);
}
