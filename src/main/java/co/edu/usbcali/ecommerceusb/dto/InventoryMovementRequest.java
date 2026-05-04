package co.edu.usbcali.ecommerceusb.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovementRequest {
    private Long inventoryId;
    private Integer quantity;
    private String movementType;
}
