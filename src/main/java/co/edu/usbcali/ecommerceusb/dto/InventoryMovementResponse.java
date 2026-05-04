package co.edu.usbcali.ecommerceusb.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovementResponse {
    private Long id;
    private Long inventoryId;
    private Integer quantity;
    private String movementType;
}
