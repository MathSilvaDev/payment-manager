package com.math.payment.application.payment.specification.filter;

import com.math.payment.domain.payment.enums.PaymentType;

import java.time.LocalDate;

public record PaymentFilter(
        String description,
        PaymentType paymentType,
        LocalDate paidDate,
        Boolean refunded
) { }
