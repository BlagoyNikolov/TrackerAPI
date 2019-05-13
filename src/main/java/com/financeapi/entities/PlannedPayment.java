package com.financeapi.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "planned_payments")
public class PlannedPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planned_payment_id")
    private Long plannedPaymentId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Account.class)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Category.class)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Currency.class)
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    private Currency currency;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Currency.class)
    @JoinColumn(name = "account_currency", referencedColumnName = "currency_id")
    private Currency accountCurrency;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @Column(name = "inserted_by")
    private String insertedBy;

    @Column(name = "account_amount")
    private BigDecimal accountAmount;

    @Column(name = "eur_amount")
    private BigDecimal eurAmount;
}
