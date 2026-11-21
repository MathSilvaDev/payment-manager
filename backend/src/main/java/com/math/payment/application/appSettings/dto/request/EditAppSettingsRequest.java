package com.math.payment.application.appSettings.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record EditAppSettingsRequest(
        @NotNull BigDecimal initialAmount
) { }
