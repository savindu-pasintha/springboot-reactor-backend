package com.example.springbootreactorbackend.event_driven.kafka_sample.DTO;

import com.example.springbootreactorbackend.event_driven.kafka_sample.Enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {

    private String orderId;
    private String userId;
    private List<OrderItemModel> items;
    private BigDecimal totalAmount;
    private OrderStatusEnum status; // e.g., CREATED, PROCESSING, COMPLETED, CANCELLED
    private String shippingAddress;
    private String billingAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String paymentId; // Optional: link to PaymentModel
    private String notes;
}
