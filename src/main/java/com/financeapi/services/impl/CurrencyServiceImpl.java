package com.financeapi.services.impl;

import com.financeapi.entities.Currency;
import com.financeapi.repositories.CurrencyRepository;
import com.financeapi.services.CurrencyService;
import com.financeapi.web.rest.resources.fx.FxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

  @Autowired
  private CurrencyRepository currencyRepository;

  @Value("${fx.baseUrl}")
  private String fxEndpoint;

  @Override
  public Optional<Currency> getCurrencyByCurrencyId(String currencyId) {
    return currencyRepository.findByCurrencyId(currencyId);
  }

  @Override
  public List<String> getAllCurrencies() {
    return currencyRepository.findAll()
        .stream()
        .map(Currency::getCurrencyId)
        .collect(Collectors.toList());
  }

  @Override
  public BigDecimal convertToAccountCurrency(Currency from, Currency to, BigDecimal amount) {
    if (from.getCurrencyId().equals(to.getCurrencyId())) {
      return amount;
    }
    RestTemplate restTemplate = new RestTemplate();
    String currencyPair = from.getCurrencyId() + "_" + to.getCurrencyId();
    String fullUrl = fxEndpoint + currencyPair;
    FxResponse response = restTemplate.getForObject(fullUrl, FxResponse.class);
    BigDecimal fxRate = BigDecimal.valueOf(response.getResults().get(currencyPair).getVal());
    return amount.multiply(fxRate);
  }

  @Override
  public BigDecimal convertToEuro(Currency from, BigDecimal amount) {
    if ("EUR".equals(from.getCurrencyId())) {
      return amount;
    }
    Currency euro = currencyRepository.findByCurrencyId("EUR").get();
    return convertToAccountCurrency(from, euro, amount);
  }
}
