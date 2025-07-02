package com.example.springbootreactorbackend.modules.payment;
import com.example.springbootreactorbackend.common.FormattedApiResponse;
import com.example.springbootreactorbackend.common.PaginationMeta;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<FormattedApiResponse<PaymentTable>> savePayment(@Valid @RequestBody PaymentTable payment) {
        PaymentTable savedPayment = paymentService.savePayment(payment);
        return ResponseEntity.ok(FormattedApiResponse.success(savedPayment,"Saved Successfully !"));
    }

    @GetMapping
    public ResponseEntity<FormattedApiResponse> getAllPayments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Page<PaymentTable> paged = paymentService.getPaginatedPayments(page, size);

        PaginationMeta paginationMeta = PaginationMeta.builder()
                .currentPage(paged.getNumber())
                .pageSize(paged.getSize())
                .totalElements(paged.getTotalElements())
                .totalPages(paged.getTotalPages())
                .build();

        return ResponseEntity.ok(FormattedApiResponse.successWithPagination(paged.getContent(),
                "Fetched Successfully !",
                paginationMeta));
    }

    @GetMapping("/delete")
    public ResponseEntity<FormattedApiResponse> deleteAllPayments() {
       if(paymentService.deleteAllPayments()){
           return ResponseEntity.ok(FormattedApiResponse.success("paymentService.deleteAllPayments()",
                   "Deleted All Successfully !"));
       }else{
        return ResponseEntity.ok(FormattedApiResponse.success("paymentService.deleteAllPayments()",
                "Deleted All UnSuccessfully !"));
       }
    }
    @GetMapping("/save")
    public ResponseEntity<FormattedApiResponse> saveBulkPayments() {
        PaymentTable mockPayment = PaymentTable.builder()
                .orderId("1-ORD-10001")
                .userId("USR-9001")
                .amount(new BigDecimal("199.99"))
                .currency(CurrencyEnum.AED)
                .paymentMethod(PaymentMethodEnum.CASH)
                .paymentDate(LocalDateTime.now())
                .transactionReference("TXN-ABC123456")
                .notes("Mock payment for testing")
                .status(PaymentStatusEnum.COMPLETED)
                .createdBy("SYSTEM")
                .updatedBy("SYSTEM")
                .build();

        List<PaymentTable> listMock = IntStream.range(1, 50000)
                .mapToObj(i -> PaymentTable.builder()
                        .orderId("ORD-" + i)
                        .userId("USR-" + i)
                        .amount(new BigDecimal("100.00"))
                        .currency(CurrencyEnum.USD)
                        .paymentMethod(PaymentMethodEnum.CASH)
                        .paymentDate(LocalDateTime.now())
                        .transactionReference("TXN-" + i)
                        .notes("Test Bulk")
                        .status(PaymentStatusEnum.COMPLETED)
                        .createdBy("SYSTEM")
                        .updatedBy("SYSTEM")
                        .build())
                .toList();

        PaymentTable savedPayment = paymentService.savePayment(mockPayment);
        List<PaymentTable> savedPayments = paymentService.saveAllPayments(listMock);

        return ResponseEntity.ok(FormattedApiResponse.success(savedPayments,"Saved Successfully !"));
    }

}