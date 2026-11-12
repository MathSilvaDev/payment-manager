package com.math.payment.application.payment.service;

import com.math.payment.application.payment.dto.request.CreatePaymentRequest;
import com.math.payment.application.payment.dto.request.EditPaymentRequest;
import com.math.payment.application.payment.dto.response.PaymentResponse;
import com.math.payment.application.payment.specification.filter.PaymentFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    @Override
    public Page<PaymentResponse> findAll(int pageNumber, int pageSize, PaymentFilter paymentFilter) {
        return null;
    }

    @Override
    public PaymentResponse create(CreatePaymentRequest request) {
        return null;
    }

    @Override
    @Transactional
    public void editById(Long id, EditPaymentRequest request) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
