package com.financeapi.exceptions.currency;

public class CurrencyNotFoundException extends RuntimeException {

  public CurrencyNotFoundException(String message) {
    super(message);
  }
}