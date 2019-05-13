package com.financeapi.services;

import com.financeapi.entities.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CurrencyService {

  Optional<Currency> getCurrencyByCurrencyId(String currencyId);

  List<String> getAllCurrencies();

  BigDecimal convertToAccountCurrency(Currency from, Currency to, BigDecimal amount);

  BigDecimal convertToEuro(Currency from, BigDecimal amount);
}
