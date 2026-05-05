package co.edu.usbcali.ecommerceusb.controller;

import co.edu.usbcali.ecommerceusb.dto.ProductCategoryRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductCategoryResponse;
import co.edu.usbcali.ecommerceusb.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<List<ProductCategoryResponse>> getAllProductCategories() {
        return new ResponseEntity<>(productCategoryService.getAllProductCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryResponse> getProductCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(productCategoryService.getProductCategoryById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductCategoryResponse> createProductCategory(@RequestBody ProductCategoryRequest request) {
        return new ResponseEntity<>(productCategoryService.save(request), HttpStatus.CREATED);
    }
}
