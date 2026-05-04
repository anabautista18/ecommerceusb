package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.ProductRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductResponse;
import co.edu.usbcali.ecommerceusb.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .available(product.getAvailable())
                .build();
    }

    public static List<ProductResponse> toProductResponseList(List<Product> products) {
        List<ProductResponse> responses = new ArrayList<>();
        for (Product product : products) {
            responses.add(toProductResponse(product));
        }
        return responses;
    }

    public static Product toProduct(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .available(request.getAvailable())
                .build();
    }
}
