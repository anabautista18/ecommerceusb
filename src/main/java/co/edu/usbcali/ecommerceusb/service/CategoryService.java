package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.CategoryRequest;
import co.edu.usbcali.ecommerceusb.dto.CategoryResponse;
import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse save(CategoryRequest categoryRequest);
}
