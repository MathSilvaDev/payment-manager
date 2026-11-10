package com.math.payment.application.payment.entity;

import com.math.payment.application.contact.entity.Contact;
import com.math.payment.application.entity.BaseEntity;
import com.math.payment.application.payment.enums.PaymentType;
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

    private LocalDate paidDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact paidBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact paidFor;

    public Payment(String description, BigDecimal amount,
                   BigDecimal feeAmount, PaymentType paymentType,
                   Contact paidBy, Contact paidFor, LocalDate paidDate) {
        this.description = description;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.paymentType = paymentType;
        this.paidBy = paidBy;
        this.paidFor = paidFor;
        this.paidDate = paidDate;
    }
}
