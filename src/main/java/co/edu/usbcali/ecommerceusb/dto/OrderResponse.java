package co.edu.usbcali.ecommerceusb.dto;

import lombok.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private Long userId;
    private String status;
    private BigDecimal totalAmount;
    private String currency;
}
