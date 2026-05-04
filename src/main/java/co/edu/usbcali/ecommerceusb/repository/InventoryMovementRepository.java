package co.edu.usbcali.ecommerceusb.repository;

import co.edu.usbcali.ecommerceusb.model.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
    List<InventoryMovement> findByInventoryId(Long inventoryId);
}
