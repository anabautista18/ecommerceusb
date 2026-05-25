package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.InventoryRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryResponse;
import co.edu.usbcali.ecommerceusb.mapper.InventoryMapper;
import co.edu.usbcali.ecommerceusb.model.Inventory;
import co.edu.usbcali.ecommerceusb.model.Product;
import co.edu.usbcali.ecommerceusb.repository.InventoryRepository;
import co.edu.usbcali.ecommerceusb.repository.ProductRepository;
import co.edu.usbcali.ecommerceusb.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    @Override
    public List<InventoryResponse> getAllInventories() {
        return InventoryMapper.toInventoryResponseList(inventoryRepository.findAll());
    }

    @Override
    public InventoryResponse getInventoryById(Long id) {
        validateId(id, "inventario");
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Inventario no encontrado con el id: %d", id)));
        return InventoryMapper.toInventoryResponse(inventory);
    }

    @Override
    public InventoryResponse save(InventoryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de inventario no puede ser nulo");
        }
        validateId(request.getProductId(), "producto");
        if (request.getQuantity() == null || request.getQuantity() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException(String.format("Producto no encontrado con el id: %d", request.getProductId())));
        return InventoryMapper.toInventoryResponse(inventoryRepository.save(InventoryMapper.toInventory(request, product)));
    }

    @Override
    public InventoryResponse update(Long id, InventoryRequest request) {
        validateId(id, "inventario");
        if (request == null) {
            throw new IllegalArgumentException("El request de inventario no puede ser nulo");
        }
        validateId(request.getProductId(), "producto");
        if (request.getQuantity() == null || request.getQuantity() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Inventario no encontrado con el id: %d", id)));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException(String.format("Producto no encontrado con el id: %d", request.getProductId())));
        existingInventory.setProduct(product);
        existingInventory.setStock(request.getQuantity());
        return InventoryMapper.toInventoryResponse(inventoryRepository.save(existingInventory));
    }

    private void validateId(Long id, String name) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Debe ingresar un id valido para " + name);
        }
    }
}
