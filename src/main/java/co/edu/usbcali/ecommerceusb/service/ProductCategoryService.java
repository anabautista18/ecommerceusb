package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.ProductCategoryRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductCategoryResponse;
import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryResponse> getAllProductCategories();
    ProductCategoryResponse getProductCategoryById(Long id);
    ProductCategoryResponse save(ProductCategoryRequest productCategoryRequest);
    ProductCategoryResponse update(Long id, ProductCategoryRequest productCategoryRequest);
    void deleteById(Long id);
}
