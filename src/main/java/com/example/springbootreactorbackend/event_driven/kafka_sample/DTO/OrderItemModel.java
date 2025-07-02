package com.example.springbootreactorbackend.event_driven.kafka_sample.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemModel {

    private String productId;
    private String productName;
    private int quantity;
    private BigDecimal price; // price per unit
}
