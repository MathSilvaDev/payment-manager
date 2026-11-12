package com.math.payment.application.contact.dto.response;

import java.time.Instant;

public record ContactResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        Instant createdAt
) { }
