package com.financeapi.web.rest.resources.transaction;

import com.financeapi.entities.PaymentType;
import com.financeapi.entities.Transaction;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class TransactionVisualizer {
    private LocalDate date;
    private BigDecimal amount;

    public TransactionVisualizer(Transaction transaction) {
        if (transaction.getType().equals(PaymentType.WITHDRAWAL)) {
            this.amount = transaction.getAmount().negate();
        } else if (transaction.getType().equals(PaymentType.DEPOSIT)) {
            this.amount = transaction.getAmount();
        }
        this.date = transaction.getDateModified().toLocalDate();
    }
}
