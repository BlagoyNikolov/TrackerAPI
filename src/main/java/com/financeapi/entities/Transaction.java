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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private BigDecimal amount;

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

    @ManyToMany
    @JoinTable(
        name = "budgets_has_transactions",
        joinColumns = @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id"),
        inverseJoinColumns = @JoinColumn(name = "budget_id", referencedColumnName = "budget_id")
    )
    private Set<Budget> budgets = new HashSet<Budget>();
}



