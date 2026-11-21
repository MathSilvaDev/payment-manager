package com.math.payment.application.payment.service;

import com.math.payment.application.payment.dto.request.CreatePaymentRequest;
import com.math.payment.application.payment.dto.request.EditPaymentRequest;
import com.math.payment.application.payment.dto.response.PaymentPageResponse;
import com.math.payment.application.payment.dto.response.PaymentResponse;
import com.math.payment.application.payment.specification.PaymentSpecification;
import com.math.payment.application.payment.specification.filter.PaymentFilter;
import com.math.payment.domain.contact.entity.Contact;
import com.math.payment.domain.contact.repository.ContactRepository;
import com.math.payment.domain.payment.entity.Payment;
import com.math.payment.domain.payment.enums.TransactionType;
import com.math.payment.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final ContactRepository contactRepository;

    @Override
    public PaymentPageResponse findAll(int pageNumber, int pageSize, PaymentFilter filter) {
        Specification<Payment> spec = PaymentSpecification.filterWith(filter);

        Pageable pageable = PageRequest.of(
                pageNumber, pageSize,
                Sort.by(
                        Sort.Order.desc("paidDate"),
                        Sort.Order.desc("createdAt")
                )
        );

        Page<PaymentResponse> paymentResponses = paymentRepository.findAll(spec, pageable)
                .map(this::toResponse);

        BigDecimal totalIncomeAmount = paymentRepository.findAll(spec)
                .stream()
                .filter(payment -> payment.getTransactionType() == TransactionType.INCOME)
                .map(payment -> payment
                        .getAmount()
                        .add(payment.getFeeAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpensiveAmount = paymentRepository.findAll(spec)
                .stream()
                .filter(payment -> payment.getTransactionType() == TransactionType.EXPENSE)
                .map(payment -> payment
                        .getAmount()
                        .add(payment.getFeeAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalAmount = totalIncomeAmount.subtract(totalExpensiveAmount);

        return toPageResponse(paymentResponses, totalAmount);
    }

    @Override
    public PaymentResponse findById(Long id) {
        Payment payment = findByIdOrThrow(id);

        return toResponse(payment);
    }

    @Override
    public PaymentResponse create(CreatePaymentRequest request) {
        Contact contactPaidBy = findContactByIdOrThrow(request.paidById());
        Contact contactPaidFor = findContactByIdOrThrow(request.paidForId());

        Payment payment = new Payment(
                request.description(),
                request.amount(),
                request.feeAmount(),
                request.paymentType(),
                request.paidDate(),
                contactPaidBy,
                contactPaidFor,
                request.transactionType()
        );

        paymentRepository.save(payment);

        return toResponse(payment);
    }

    @Override
    @Transactional
    public void editById(Long id, EditPaymentRequest request) {
        Payment payment = findByIdOrThrow(id);

        payment.edit(
                request.description(),
                request.amount(),
                request.feeAmount(),
                request.paidDate(),
                request.refunded()
        );
    }

    @Override
    public void deleteById(Long id) {
        Payment payment = findByIdOrThrow(id);

        paymentRepository.delete(payment);
    }

    private PaymentResponse toResponse(Payment payment){
        return new PaymentResponse(
                payment.getId(),
                payment.getDescription(),
                payment.getAmount(),
                payment.getFeeAmount(),
                payment.getPaymentType(),
                payment.getPaidDate(),
                payment.getPaidBy().getName(),
                payment.getPaidFor().getName(),
                payment.isRefunded(),
                payment.getTransactionType(),
                payment.getCreatedAt()
        );
    }

    private PaymentPageResponse toPageResponse(Page<PaymentResponse> pageResponse,
                                               BigDecimal value){
        return new PaymentPageResponse(
                pageResponse,
                value
        );
    }

    private Payment findByIdOrThrow(Long id){
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Payment not found"));
    }

    private Contact findContactByIdOrThrow(Long id){
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Contact not found"));
    }

}
