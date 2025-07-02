package com.example.springbootreactorbackend.modules.payment;

public enum PaymentMethodEnum {
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    BANK_TRANSFER,
    CASH,
    UPI,              // Unified Payments Interface (India)
    STRIPE,           // Stripe Payment Gateway
    APPLE_PAY,
    GOOGLE_PAY,
    SAMSUNG_PAY,
    GRAB_PAY,         // Common in Southeast Asia
    ALIPAY,           // China
    WECHAT_PAY,       // China
    NET_BANKING       // Common in Asia
}
