package com.math.payment.application.payment.specification.filter;

import com.math.payment.domain.payment.enums.PaymentType;
import com.math.payment.domain.payment.enums.TransactionType;

import java.time.LocalDate;

public record PaymentFilter(
        String description,
        TransactionType transactionType,
        PaymentType paymentType,
        LocalDate startPaidDate,
        LocalDate endPaidDate,
        Boolean refunded
) { }
