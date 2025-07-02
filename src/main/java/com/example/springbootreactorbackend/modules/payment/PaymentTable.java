package com.example.springbootreactorbackend.modules.payment;

import com.example.springbootreactorbackend.custom_annotations.ValidCurrency;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "payment_id", updatable = false, nullable = false)
    private String paymentId;

    @NotBlank(message = "Order ID is required")
    @Size(min = 5, max = 50, message = "Order ID must be between 5-50 characters")
    @Column(name = "order_id", nullable = false)
    private String orderId;

    @NotBlank(message = "User ID is required")
    @Size(min = 5, max = 50, message = "User ID must be between 5-50 characters")
    @Column(name = "user_id", nullable = false)
    private String userId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be at least 0.01")
    @DecimalMax(value = "1000000.00", message = "Amount cannot exceed 1,000,000.00")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @ValidCurrency
    @NotNull(message = "Currency is required")
    @Enumerated(EnumType.STRING)
    @Column(length = 3, nullable = false)
    private CurrencyEnum currency;

    @NotNull(message = "Payment method is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethodEnum paymentMethod;

    @PastOrPresent(message = "Payment date cannot be in the future")
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Size(max = 50, message = "Transaction reference cannot exceed 50 characters")
    @Column(name = "transaction_reference")
    private String transactionReference;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    @Column(length = 500)
    private String notes;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatusEnum status;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        if (this.paymentDate == null) {
            this.paymentDate = LocalDateTime.now();
        }
    }
}