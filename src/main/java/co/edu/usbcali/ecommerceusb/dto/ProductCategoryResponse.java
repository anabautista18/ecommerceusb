package co.edu.usbcali.ecommerceusb.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryResponse {
    private Long id;
    private Long productId;
    private Long categoryId;
}
