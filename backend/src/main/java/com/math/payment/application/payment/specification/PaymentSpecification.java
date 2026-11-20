package com.math.payment.application.payment.specification;

import com.math.payment.application.payment.specification.filter.PaymentFilter;
import com.math.payment.domain.payment.entity.Payment;
import com.math.payment.domain.payment.enums.PaymentType;
import com.math.payment.domain.payment.enums.TransactionType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PaymentSpecification {

    public static Specification<Payment> filterWith(PaymentFilter filter){
        return Specification
                .where(containsDescriptionIgnoreCaseSensitive(filter.description()))
                .and(transactionTypeEquals(filter.transactionType()))
                .and(paymentTypeEquals(filter.paymentType()))
                .and(paymentDateBetween(filter.startPaidDate(), filter.endPaidDate()))
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

    private static Specification<Payment> transactionTypeEquals(TransactionType transactionType){

        return (root, query, cb) ->
                transactionType == null
                        ? null
                        : cb.equal(root.get("transactionType"), transactionType.name());
    }

    private static Specification<Payment> paymentTypeEquals(PaymentType paymentType){

        return (root, query, cb) ->
                paymentType == null
                        ? null
                        : cb.equal(root.get("paymentType"), paymentType.name());
    }

    private static Specification<Payment> paymentDateBetween(LocalDate startPaidDate,
                                                             LocalDate endPaidDate){
        return (root, query, cb) -> {
            if(startPaidDate == null && endPaidDate == null){
                return null;
            }
            else if(startPaidDate != null && endPaidDate != null){
                return cb.between(root.get("paidDate"), startPaidDate, endPaidDate);
            }
            else if(startPaidDate != null){
                return cb.greaterThanOrEqualTo(root.get("paidDate"), startPaidDate);
            }
            return cb.lessThanOrEqualTo(root.get("paidDate"), endPaidDate);
        };

    }

    private static Specification<Payment> refundedEquals(Boolean refunded){
        return (root, query, cb) ->
                refunded == null
                        ? null
                        : cb.equal(root.get("refunded"), refunded);
    }
}
