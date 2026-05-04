package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.PaymentRequest;
import co.edu.usbcali.ecommerceusb.dto.PaymentResponse;
import java.util.List;

public interface PaymentService {
    List<PaymentResponse> getAllPayments();
    PaymentResponse getPaymentById(Long id);
    PaymentResponse save(PaymentRequest paymentRequest);
}
