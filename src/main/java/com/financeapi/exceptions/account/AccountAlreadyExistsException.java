package com.financeapi.exceptions.account;

public class AccountAlreadyExistsException extends Exception {
  public AccountAlreadyExistsException(String message) {
    super(message);
  }
}
