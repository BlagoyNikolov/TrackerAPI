package com.financeapi.repositories;

import com.financeapi.entities.PlannedPayment;
import com.financeapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PlannedPaymentRepository extends JpaRepository<PlannedPayment, Long> {

  List<PlannedPayment> findByAccountAccountId(Long accountId);

  List<PlannedPayment> findByCategoryCategoryId(Long categoryId);

  PlannedPayment findByPlannedPaymentId(Long plannedPaymentId);

  List<PlannedPayment> findByAccountUser(User user);

  List<PlannedPayment> findAllByFromDate(LocalDateTime localDateTime);

  List<PlannedPayment> findAllByDescriptionContaining(String keyword);
}
