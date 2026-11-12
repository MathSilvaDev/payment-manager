package com.math.payment.application.payment.service;

import com.math.payment.application.contact.specification.ContactSpecification;
import com.math.payment.application.payment.dto.request.CreatePaymentRequest;
import com.math.payment.application.payment.dto.request.EditPaymentRequest;
import com.math.payment.application.payment.dto.response.PaymentResponse;
import com.math.payment.application.payment.specification.PaymentSpecification;
import com.math.payment.application.payment.specification.filter.PaymentFilter;
import com.math.payment.domain.contact.entity.Contact;
import com.math.payment.domain.contact.repository.ContactRepository;
import com.math.payment.domain.payment.entity.Payment;
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

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final ContactRepository contactRepository;

    @Override
    public Page<PaymentResponse> findAll(int pageNumber, int pageSize, PaymentFilter filter) {
        Specification<Payment> spec = PaymentSpecification.filterWith(filter);
        Pageable pageable = PageRequest.of(
                pageNumber, pageSize,
                Sort.by(
                        Sort.Order.desc("paidDate"),
                        Sort.Order.desc("createdAt")
                )
        );

        return paymentRepository.findAll(spec, pageable)
                .map(this::toResponse);
    }

    @Override
    public PaymentResponse create(CreatePaymentRequest request) {
        Contact contactPaidBy = findContactById(request.paidById());
        Contact contactPaidFor = findContactById(request.paidForId());

        Payment payment = new Payment(
                request.description(),
                request.amount(),
                request.feeAmount(),
                request.paymentType(),
                request.paidDate(),
                contactPaidBy,
                contactPaidFor
        );

        paymentRepository.save(payment);

        return toResponse(payment);
    }

    @Override
    @Transactional
    public void editById(Long id, EditPaymentRequest request) {

    }

    @Override
    public void deleteById(Long id) {

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
                payment.getCreatedAt()
        );
    }

    private Contact findContactById(Long id){
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Contact not found"));
    }
}
