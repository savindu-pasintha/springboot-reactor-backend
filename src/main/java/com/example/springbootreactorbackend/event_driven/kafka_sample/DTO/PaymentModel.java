package com.example.springbootreactorbackend.event_driven.kafka_sample.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.springbootreactorbackend.event_driven.kafka_sample.Enums.PaymentStatusEnum;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentModel {
    private String paymentId;
    private String orderId;
    private String userId;
    private BigDecimal amount;
    private String currency; // e.g., "USD", "SGD"
    private String paymentMethod; // e.g., "CREDIT_CARD", "PAYPAL", "BANK_TRANSFER"
    private LocalDateTime paymentDate;
    private LocalDateTime updatedAt;
    private String transactionReference; // Optional: gateway transaction ID
    private String notes;
    private PaymentStatusEnum status; // enum: PENDING, COMPLETED, FAILED, REFUNDED

}

