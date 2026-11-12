package com.math.payment.application.payment.controller;

import com.math.payment.application.payment.dto.request.CreatePaymentRequest;
import com.math.payment.application.payment.dto.request.EditPaymentRequest;
import com.math.payment.application.payment.dto.response.PaymentResponse;
import com.math.payment.application.payment.service.PaymentServiceImpl;
import com.math.payment.application.payment.specification.filter.PaymentFilter;
import jakarta.validation.Valid;
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

    @PostMapping(version = "1.0")
    public ResponseEntity<PaymentResponse> create(
            @Valid @RequestBody CreatePaymentRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentService.create(request));
    }

    @PatchMapping(path = "/{id}", version = "1.0")
    public ResponseEntity<Void> editById(@PathVariable Long id,
                                         @Valid @RequestBody EditPaymentRequest request){
        paymentService.editById(id, request);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping(path = "/{id}", version = "1.0")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        paymentService.deleteById(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
