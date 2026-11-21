package com.math.payment.application.appSettings.dto.response;

import java.math.BigDecimal;

public record AppSettingsResponse(
        BigDecimal initialAmount
) { }
