package com.math.payment.application.payment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EditPaymentRequest(
        @NotBlank String description,
        @NotNull BigDecimal amount,
        @NotNull BigDecimal feeAmount,
        @NotNull LocalDate paidDate,
        boolean refunded
) {}
