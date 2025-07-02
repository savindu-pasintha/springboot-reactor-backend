package com.example.springbootreactorbackend.event_driven.kafka_sample.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private String password;
    private String phoneNumber;
    private String address;
    private String role;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

