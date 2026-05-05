package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.InventoryRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryResponse;
import co.edu.usbcali.ecommerceusb.model.Inventory;
import co.edu.usbcali.ecommerceusb.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InventoryMapper {
    public static InventoryResponse toInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .id(inventory.getId())
                .productId(inventory.getProduct() != null ? inventory.getProduct().getId() : null)
                .quantity(inventory.getStock())
                .build();
    }

    public static List<InventoryResponse> toInventoryResponseList(List<Inventory> inventories) {
        List<InventoryResponse> responses = new ArrayList<>();
        for (Inventory inventory : inventories) {
            responses.add(toInventoryResponse(inventory));
        }
        return responses;
    }

    public static Inventory toInventory(InventoryRequest request, Product product) {
        return Inventory.builder()
                .product(product)
                .stock(request.getQuantity())
                .build();
    }
}
