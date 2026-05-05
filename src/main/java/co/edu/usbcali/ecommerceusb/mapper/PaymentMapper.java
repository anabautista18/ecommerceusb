package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.PaymentRequest;
import co.edu.usbcali.ecommerceusb.dto.PaymentResponse;
import co.edu.usbcali.ecommerceusb.model.Payment;
import co.edu.usbcali.ecommerceusb.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentMapper {
    public static PaymentResponse toPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrder() != null ? payment.getOrder().getId() : null)
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .build();
    }

    public static List<PaymentResponse> toPaymentResponseList(List<Payment> payments) {
        List<PaymentResponse> responses = new ArrayList<>();
        for (Payment payment : payments) {
            responses.add(toPaymentResponse(payment));
        }
        return responses;
    }

    public static Payment toPayment(PaymentRequest request, Order order) {
        return Payment.builder()
                .order(order)
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .status(request.getStatus())
                .idempotencyKey(UUID.randomUUID().toString())
                .build();
    }
}
