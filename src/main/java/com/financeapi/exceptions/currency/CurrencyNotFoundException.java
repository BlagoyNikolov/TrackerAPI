package com.financeapi.exceptions.currency;

public class CurrencyNotFoundException extends Exception {
  public CurrencyNotFoundException(String message) {
    super(message);
  }
}