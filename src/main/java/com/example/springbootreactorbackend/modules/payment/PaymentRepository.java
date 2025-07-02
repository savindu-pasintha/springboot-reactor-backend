package com.example.springbootreactorbackend.modules.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentTable, String> {
    // Add custom queries if needed
}

