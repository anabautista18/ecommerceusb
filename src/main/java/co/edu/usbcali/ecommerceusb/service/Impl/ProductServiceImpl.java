package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.ProductRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductResponse;
import co.edu.usbcali.ecommerceusb.mapper.ProductMapper;
import co.edu.usbcali.ecommerceusb.model.Product;
import co.edu.usbcali.ecommerceusb.repository.ProductRepository;
import co.edu.usbcali.ecommerceusb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
        return ProductMapper.toProductResponseList(productRepository.findAll());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        validateId(id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Producto no encontrado con el id: %d", id)));
        return ProductMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse save(ProductRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de producto no puede ser nulo");
        }
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacio");
        }
        if (request.getPrice() == null || request.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        }
        if (request.getAvailable() == null) {
            throw new IllegalArgumentException("El campo available no puede ser nulo");
        }
        return ProductMapper.toProductResponse(productRepository.save(ProductMapper.toProduct(request)));
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Debe ingresar un id valido para buscar producto");
        }
    }
}
