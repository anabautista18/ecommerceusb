package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.ProductRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductResponse;
import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long id);
    ProductResponse save(ProductRequest productRequest);
    ProductResponse update(Long id, ProductRequest productRequest);
}
