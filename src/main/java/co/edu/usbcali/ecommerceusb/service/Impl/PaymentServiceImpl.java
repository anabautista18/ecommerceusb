package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.PaymentRequest;
import co.edu.usbcali.ecommerceusb.dto.PaymentResponse;
import co.edu.usbcali.ecommerceusb.mapper.PaymentMapper;
import co.edu.usbcali.ecommerceusb.model.Order;
import co.edu.usbcali.ecommerceusb.model.Payment;
import co.edu.usbcali.ecommerceusb.repository.OrderRepository;
import co.edu.usbcali.ecommerceusb.repository.PaymentRepository;
import co.edu.usbcali.ecommerceusb.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<PaymentResponse> getAllPayments() {
        return PaymentMapper.toPaymentResponseList(paymentRepository.findAll());
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        validateId(id, "pago");
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Pago no encontrado con el id: %d", id)));
        return PaymentMapper.toPaymentResponse(payment);
    }

    @Override
    public PaymentResponse save(PaymentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de pago no puede ser nulo");
        }
        validateId(request.getOrderId(), "orden");
        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0");
        }
        if (request.getPaymentMethod() == null || request.getPaymentMethod().isBlank()) {
            throw new IllegalArgumentException("El metodo de pago no puede ser nulo ni vacio");
        }
        if (request.getStatus() == null || request.getStatus().isBlank()) {
            throw new IllegalArgumentException("El estado no puede ser nulo ni vacio");
        }
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException(String.format("Orden no encontrada con el id: %d", request.getOrderId())));
        return PaymentMapper.toPaymentResponse(paymentRepository.save(PaymentMapper.toPayment(request, order)));
    }

    private void validateId(Long id, String name) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Debe ingresar un id valido para " + name);
        }
    }
}
