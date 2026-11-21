package com.math.payment.application.payment.dto.response;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record PaymentPageResponse (
        Page<PaymentResponse> paymentResponses,
        BigDecimal totalAmount
){ }
