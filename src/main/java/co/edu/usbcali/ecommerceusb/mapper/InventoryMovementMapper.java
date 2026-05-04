package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.InventoryMovementRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryMovementResponse;
import co.edu.usbcali.ecommerceusb.model.InventoryMovement;
import co.edu.usbcali.ecommerceusb.model.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryMovementMapper {
    public static InventoryMovementResponse toInventoryMovementResponse(InventoryMovement movement) {
        return InventoryMovementResponse.builder()
                .id(movement.getId())
                .inventoryId(movement.getInventory() != null ? movement.getInventory().getId() : null)
                .quantity(movement.getQuantity())
                .movementType(movement.getMovementType())
                .build();
    }

    public static List<InventoryMovementResponse> toInventoryMovementResponseList(List<InventoryMovement> movements) {
        List<InventoryMovementResponse> responses = new ArrayList<>();
        for (InventoryMovement movement : movements) {
            responses.add(toInventoryMovementResponse(movement));
        }
        return responses;
    }

    public static InventoryMovement toInventoryMovement(InventoryMovementRequest request, Inventory inventory) {
        return InventoryMovement.builder()
                .inventory(inventory)
                .quantity(request.getQuantity())
                .movementType(request.getMovementType())
                .build();
    }
}
