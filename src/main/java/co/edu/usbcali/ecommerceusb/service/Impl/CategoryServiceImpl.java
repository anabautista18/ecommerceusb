package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CategoryRequest;
import co.edu.usbcali.ecommerceusb.dto.CategoryResponse;
import co.edu.usbcali.ecommerceusb.mapper.CategoryMapper;
import co.edu.usbcali.ecommerceusb.model.Category;
import co.edu.usbcali.ecommerceusb.repository.CategoryRepository;
import co.edu.usbcali.ecommerceusb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategories() {
        return CategoryMapper.toCategoryResponseList(categoryRepository.findAll());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        validateId(id, "categoria");
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Categoria no encontrada con el id: %d", id)));
        return CategoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse save(CategoryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de categoria no puede ser nulo");
        }
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacio");
        }
        Category parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException(String.format("Categoria padre no encontrada con el id: %d", request.getParentId())));
        }
        return CategoryMapper.toCategoryResponse(categoryRepository.save(CategoryMapper.toCategory(request, parent)));
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        validateId(id, "categoria");
        if (request == null) {
            throw new IllegalArgumentException("El request de categoria no puede ser nulo");
        }
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacio");
        }
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Categoria no encontrada con el id: %d", id)));
        Category parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException(String.format("Categoria padre no encontrada con el id: %d", request.getParentId())));
        }
        existingCategory.setName(request.getName());
        existingCategory.setParent(parent);
        return CategoryMapper.toCategoryResponse(categoryRepository.save(existingCategory));
    }

    @Override
    public void deleteById(Long id) {
        validateId(id, "categoria");
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Categoria no encontrada con el id: %d", id)));
        categoryRepository.delete(existingCategory);
    }

    private void validateId(Long id, String name) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Debe ingresar un id valido para buscar " + name);
        }
    }
}
