package com.financeapi.repositories;

import com.financeapi.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

  Optional<Currency> findByCurrencyId(String currencyId);
}
