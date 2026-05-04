package co.edu.usbcali.ecommerceusb.dto;

import lombok.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
}
