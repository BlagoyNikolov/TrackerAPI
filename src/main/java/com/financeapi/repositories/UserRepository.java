package com.financeapi.repositories;

import com.financeapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "UPDATE User SET password = ?1 WHERE userId = ?2")
  void updateUserPassword(User user, String password);

  Optional<User> findByUsername(String username);

  boolean existsUserByUsername(String username);

  boolean existsUserByEmail(String email);

  Optional<User> findByEmail(String email);

  Optional<User> getByUserId(Long userId);
}
