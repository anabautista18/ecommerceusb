package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.ProductCategoryRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductCategoryResponse;
import co.edu.usbcali.ecommerceusb.mapper.ProductCategoryMapper;
import co.edu.usbcali.ecommerceusb.model.Category;
import co.edu.usbcali.ecommerceusb.model.Product;
import co.edu.usbcali.ecommerceusb.model.ProductCategory;
import co.edu.usbcali.ecommerceusb.repository.CategoryRepository;
import co.edu.usbcali.ecommerceusb.repository.ProductCategoryRepository;
import co.edu.usbcali.ecommerceusb.repository.ProductRepository;
import co.edu.usbcali.ecommerceusb.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductCategoryResponse> getAllProductCategories() {
        return ProductCategoryMapper.toProductCategoryResponseList(productCategoryRepository.findAll());
    }

    @Override
    public ProductCategoryResponse getProductCategoryById(Long id) {
        validateId(id, "categoria de producto");
        ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Categoria de producto no encontrada con el id: %d", id)));
        return ProductCategoryMapper.toProductCategoryResponse(productCategory);
    }

    @Override
    public ProductCategoryResponse save(ProductCategoryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de categoria de producto no puede ser nulo");
        }
        validateId(request.getProductId(), "producto");
        validateId(request.getCategoryId(), "categoria");
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException(String.format("Producto no encontrado con el id: %d", request.getProductId())));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException(String.format("Categoria no encontrada con el id: %d", request.getCategoryId())));
        return ProductCategoryMapper.toProductCategoryResponse(
                productCategoryRepository.save(ProductCategoryMapper.toProductCategory(request, product, category)));
    }

    private void validateId(Long id, String name) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Debe ingresar un id valido para " + name);
        }
    }
}
