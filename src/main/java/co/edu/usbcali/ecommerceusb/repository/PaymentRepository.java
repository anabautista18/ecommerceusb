package co.edu.usbcali.ecommerceusb.repository;

import co.edu.usbcali.ecommerceusb.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrderId(Long orderId);
    List<Payment> findByStatus(String status);
}
