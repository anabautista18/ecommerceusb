package co.edu.usbcali.ecommerceusb.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private Long id;
    private Long cartId;
    private Long productId;
    private Integer quantity;
}
