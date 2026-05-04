package co.edu.usbcali.ecommerceusb.dto;

import lombok.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private Long id;
    private Long orderId;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
}
