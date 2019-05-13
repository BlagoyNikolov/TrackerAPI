package com.financeapi.repositories;

import com.financeapi.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  List<Transaction> findByAccountAccountId(Long accountId);

  List<Transaction> findByCategoryCategoryId(Long categoryId);

  Transaction findByTransactionId(Long transactionId);

  List<Transaction> findByCategoryAndAccount(Category category, Account account);

  List<Transaction> findAllByDescriptionContaining(String keyword);
}
