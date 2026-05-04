package co.edu.usbcali.ecommerceusb.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private Long id;
    private Long userId;
    private String status;
}
