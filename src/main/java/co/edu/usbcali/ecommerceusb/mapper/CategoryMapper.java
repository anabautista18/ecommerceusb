package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.CategoryRequest;
import co.edu.usbcali.ecommerceusb.dto.CategoryResponse;
import co.edu.usbcali.ecommerceusb.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .build();
    }

    public static List<CategoryResponse> toCategoryResponseList(List<Category> categories) {
        List<CategoryResponse> responses = new ArrayList<>();
        for (Category category : categories) {
            responses.add(toCategoryResponse(category));
        }
        return responses;
    }

    public static Category toCategory(CategoryRequest request, Category parent) {
        return Category.builder()
                .name(request.getName())
                .parent(parent)
                .build();
    }
}
