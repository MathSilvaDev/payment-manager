package com.math.payment.application.payment.dto.request;

import com.math.payment.domain.payment.enums.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePaymentRequest(
        @NotBlank String description,
        @NotNull BigDecimal amount,
        @NotNull BigDecimal feeAmount,
        @NotNull PaymentType paymentType,
        @NotNull LocalDate paidDate,
        Long paidById,
        Long paidForId
) {}
