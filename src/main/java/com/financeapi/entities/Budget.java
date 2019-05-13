package com.financeapi.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private Long budgetId;

    @Column(name = "name")
    private String name;

    @Column(name = "initial_amount")
    private BigDecimal initialAmount;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @Column(name = "to_date")
    private LocalDateTime toDate;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Account.class)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Category.class)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Currency.class)
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    private Currency currency;

    @Column(name = "inserted_by")
    private String insertedBy;

    @ManyToMany
    @JoinTable(
        name = "budgets_has_transactions",
        joinColumns = @JoinColumn(name = "budget_id", referencedColumnName = "budget_id"),
        inverseJoinColumns = @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    )
    private Set<Transaction> transactions = new HashSet<Transaction>();
}
