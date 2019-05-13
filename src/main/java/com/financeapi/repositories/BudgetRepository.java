package com.financeapi.repositories;

import com.financeapi.entities.Account;
import com.financeapi.entities.Budget;
import com.financeapi.entities.Category;
import com.financeapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

  List<Budget> findByCategoryAndAccount(Category category, Account account);

  List<Budget> findByAccountUser(User user);

  Budget findByBudgetId(Long budgetId);
}
