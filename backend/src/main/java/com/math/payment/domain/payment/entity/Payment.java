package com.math.payment.domain.payment.entity;

import com.math.payment.domain.contact.entity.Contact;
import com.math.payment.domain.entity.BaseEntity;
import com.math.payment.domain.payment.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal feeAmount;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    @Column(nullable = false)
    private LocalDate paidDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paid_by_contact_id")
    private Contact paidBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paid_for_contact_id")
    private Contact paidFor;

    private boolean refunded;

    public Payment(String description, BigDecimal amount,
                   BigDecimal feeAmount, PaymentType paymentType,
                   LocalDate paidDate, Contact paidBy, Contact paidFor) {
        this.description = description;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.paymentType = paymentType;
        this.paidDate = paidDate;
        this.paidBy = paidBy;
        this.paidFor = paidFor;
    }

    public void edit(String description, BigDecimal amount, BigDecimal feeAmount,
                     PaymentType paymentType, LocalDate paidDate,
                     Contact paidBy, Contact paidFor, boolean refunded) {
        this.description = description;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.paymentType = paymentType;
        this.paidDate = paidDate;
        this.paidBy = paidBy;
        this.paidFor = paidFor;
        this.refunded = refunded;
    }
}
