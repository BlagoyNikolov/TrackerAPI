package com.financeapi.exceptions.account;

public class AccountAlreadyExistsException extends RuntimeException {

  public AccountAlreadyExistsException(String message) {
    super(message);
  }
}
