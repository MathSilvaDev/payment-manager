package com.math.payment.application.payment.dto.response;

import com.math.payment.domain.payment.enums.PaymentType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record PaymentResponse(
        Long id,
        String description,
        BigDecimal amount,
        BigDecimal feeAmount,
        PaymentType paymentType,
        LocalDate paidDate,
        String paidBy,
        String paidFor,
        Boolean refunded,
        Instant createdAt
) { }
