package com.math.payment.application.payment.service;

import com.math.payment.application.payment.dto.request.CreatePaymentRequest;
import com.math.payment.application.payment.dto.request.EditPaymentRequest;
import com.math.payment.application.payment.dto.response.PaymentResponse;
import com.math.payment.application.payment.specification.filter.PaymentFilter;
import org.springframework.data.domain.Page;

public interface PaymentService {

    Page<PaymentResponse> findAll(int pageNumber, int pageSize, PaymentFilter paymentFilter);

    PaymentResponse create(CreatePaymentRequest request);

    void editById(Long id, EditPaymentRequest request);

    void deleteById(Long id);
}
