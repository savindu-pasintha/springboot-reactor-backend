package com.example.springbootreactorbackend.modules.payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Save a payment
    public PaymentTable savePayment(PaymentTable payment) {
        return paymentRepository.save(payment);
    }

    // Get all payments
    public List<PaymentTable> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Page<PaymentTable> getPaginatedPayments(int page, int size) {
        return paymentRepository.findAll(PageRequest.of(page, size));
    }

    public List<PaymentTable> saveAllPayments(List<PaymentTable> payments) {
        return paymentRepository.saveAll(payments);
    }

    public boolean  deleteAllPayments() {
         paymentRepository.deleteAll();
         return true;
    }


}
