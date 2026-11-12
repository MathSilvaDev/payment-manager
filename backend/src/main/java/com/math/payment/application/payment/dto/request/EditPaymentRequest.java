package com.math.payment.application.payment.dto.request;

import com.math.payment.domain.payment.enums.PaymentType;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EditPaymentRequest(
        @NotBlank String description,
        @NotBlank BigDecimal amount,
        @NotBlank BigDecimal feeAmount,
        @NotBlank PaymentType paymentType,
        @NotBlank LocalDate paidDate,
        Long paidById,
        Long paidForId,
        boolean refunded
) {}
