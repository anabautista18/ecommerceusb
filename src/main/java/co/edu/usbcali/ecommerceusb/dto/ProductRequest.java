package co.edu.usbcali.ecommerceusb.dto;

import lombok.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
}
