package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.ProductCategoryRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductCategoryResponse;
import co.edu.usbcali.ecommerceusb.model.ProductCategory;
import co.edu.usbcali.ecommerceusb.model.Product;
import co.edu.usbcali.ecommerceusb.model.Category;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryMapper {
    public static ProductCategoryResponse toProductCategoryResponse(ProductCategory productCategory) {
        return ProductCategoryResponse.builder()
                .id(productCategory.getId())
                .productId(productCategory.getProduct() != null ? productCategory.getProduct().getId() : null)
                .categoryId(productCategory.getCategory() != null ? productCategory.getCategory().getId() : null)
                .build();
    }

    public static List<ProductCategoryResponse> toProductCategoryResponseList(List<ProductCategory> productCategories) {
        List<ProductCategoryResponse> responses = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            responses.add(toProductCategoryResponse(productCategory));
        }
        return responses;
    }

    public static ProductCategory toProductCategory(ProductCategoryRequest request, Product product, Category category) {
        return ProductCategory.builder()
                .product(product)
                .category(category)
                .build();
    }
}
