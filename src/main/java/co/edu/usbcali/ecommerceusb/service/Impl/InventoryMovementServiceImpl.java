package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.InventoryMovementRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryMovementResponse;
import co.edu.usbcali.ecommerceusb.mapper.InventoryMovementMapper;
import co.edu.usbcali.ecommerceusb.model.Inventory;
import co.edu.usbcali.ecommerceusb.model.InventoryMovement;
import co.edu.usbcali.ecommerceusb.repository.InventoryMovementRepository;
import co.edu.usbcali.ecommerceusb.repository.InventoryRepository;
import co.edu.usbcali.ecommerceusb.service.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryMovementServiceImpl implements InventoryMovementService {
    private final InventoryMovementRepository inventoryMovementRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryMovementResponse> getAllInventoryMovements() {
        return InventoryMovementMapper.toInventoryMovementResponseList(inventoryMovementRepository.findAll());
    }

    @Override
    public InventoryMovementResponse getInventoryMovementById(Long id) {
        validateId(id, "movimiento de inventario");
        InventoryMovement movement = inventoryMovementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Movimiento de inventario no encontrado con el id: %d", id)));
        return InventoryMovementMapper.toInventoryMovementResponse(movement);
    }

    @Override
    public InventoryMovementResponse save(InventoryMovementRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de movimiento de inventario no puede ser nulo");
        }
        validateId(request.getInventoryId(), "inventario");
        if (request.getQuantity() == null || request.getQuantity() == 0) {
            throw new IllegalArgumentException("La cantidad debe ser diferente de 0");
        }
        if (request.getMovementType() == null || request.getMovementType().isBlank()) {
            throw new IllegalArgumentException("El tipo de movimiento no puede ser nulo ni vacio");
        }
        Inventory inventory = inventoryRepository.findById(request.getInventoryId())
                .orElseThrow(() -> new RuntimeException(String.format("Inventario no encontrado con el id: %d", request.getInventoryId())));
        return InventoryMovementMapper.toInventoryMovementResponse(
                inventoryMovementRepository.save(InventoryMovementMapper.toInventoryMovement(request, inventory)));
    }

    private void validateId(Long id, String name) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Debe ingresar un id valido para " + name);
        }
    }
}
