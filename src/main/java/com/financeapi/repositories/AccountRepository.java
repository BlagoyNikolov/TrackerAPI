package com.financeapi.repositories;

import com.financeapi.entities.Account;
import com.financeapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

  Optional<Account> findByUserUserIdAndName(Long userId, String name);

  boolean existsByUserUserIdAndName(Long userId, String name);

  List<Account> findByUser(User user);

  Optional<Account> findByAccountId(Long accountId);
}
