package com.math.payment.application.payment.dto.response;

import com.math.payment.domain.payment.enums.PaymentType;
import com.math.payment.domain.payment.enums.TransactionType;

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
        TransactionType transactionType,
        Instant createdAt
) { }
