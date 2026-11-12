package com.math.payment.application.payment.specification;

import com.math.payment.application.payment.specification.filter.PaymentFilter;
import com.math.payment.domain.payment.entity.Payment;
import com.math.payment.domain.payment.enums.PaymentType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PaymentSpecification {

    public static Specification<Payment> filterWith(PaymentFilter filter){
        return Specification
                .where(containsDescriptionIgnoreCaseSensitive(filter.description()))
                .and(containsPaymentTypeIgnoreCaseSensitive(filter.paymentType()))
                .and(paymentDateEquals(filter.paidDate()))
                .and(refundedEquals(filter.refunded()));
    }

    private static Specification<Payment>
        containsDescriptionIgnoreCaseSensitive(String description){

        return (root, query, cb) ->
                description == null
                        ? null
                        : cb.like(cb.lower(root.get("description")),
                        "%" + description.toLowerCase() + "%");
    }

    private static Specification<Payment>
        containsPaymentTypeIgnoreCaseSensitive(PaymentType paymentType){

        return (root, query, cb) ->
                paymentType == null
                        ? null
                        : cb.like(cb.lower(root.get("paymentType")),
                        "%" + paymentType.name().toLowerCase() + "%");
    }

    private static Specification<Payment> paymentDateEquals(LocalDate paidDate){
        return (root, query, cb) ->
                paidDate == null
                        ? null
                        : cb.equal(root.get("paidDate"), paidDate);
    }

    private static Specification<Payment> refundedEquals(Boolean refunded){
        return (root, query, cb) ->
                refunded == null
                        ? null
                        : cb.equal(root.get("refunded"), refunded);
    }
}
