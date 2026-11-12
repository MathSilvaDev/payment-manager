package com.math.payment.application.payment.controller;

import com.math.payment.application.contact.dto.response.ContactResponse;
import com.math.payment.application.contact.specification.filter.ContactFilter;
import com.math.payment.application.payment.dto.response.PaymentResponse;
import com.math.payment.application.payment.service.PaymentServiceImpl;
import com.math.payment.application.payment.specification.filter.PaymentFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @GetMapping(version = "1.0")
    public ResponseEntity<Page<PaymentResponse>> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize,
            @ModelAttribute PaymentFilter paymentFilter){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paymentService.findAll(pageNumber, pageSize, paymentFilter));
    }
}
