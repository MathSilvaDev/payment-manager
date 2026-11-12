package com.math.payment.application.contact.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateContactRequest(
    @NotBlank String name,
    @Email String email,
    String phoneNumber
) { }
