package com.financeapi.services;

import com.financeapi.entities.Currency;
import com.financeapi.web.rest.resources.currency.CurrencyResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CurrencyService {

  Optional<Currency> getCurrency(String currencyId);

  CurrencyResponse getCurrencyById(String currencyId);

  List<CurrencyResponse> getAllCurrencies();

  BigDecimal convertToAccountCurrency(Currency from, Currency to, BigDecimal amount);

  BigDecimal convertToEuro(Currency from, BigDecimal amount);
}
