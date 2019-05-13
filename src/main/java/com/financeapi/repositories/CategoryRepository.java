package com.financeapi.repositories;

import com.financeapi.entities.Category;
import com.financeapi.entities.PaymentType;
import com.financeapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Category findByCategoryId(Long categoryId);

  List<Category> findByUserIsNull();

  List<Category> findByUserUserId(Long userId);

  Category findByName(String categoryName);

  List<Category> findAllByType(PaymentType type);

  @Query(value = "SELECT c FROM Category c WHERE (user_id = ?1 OR user_id IS NULL) AND type = ?2")
  List<Category> getCategories(User user, PaymentType type);
}
