package com.financeapi.web.rest;

import com.financeapi.exceptions.InvalidInputException;
import com.financeapi.exceptions.account.AccountAlreadyExistsException;
import com.financeapi.exceptions.account.AccountNotFoundException;
import com.financeapi.exceptions.currency.CurrencyNotFoundException;
import com.financeapi.exceptions.user.UserNotFoundException;
import com.financeapi.web.rest.resources.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({InvalidInputException.class})
  public ErrorResponse handleInvalidInputException(Exception e) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({AccountNotFoundException.class})
  public ErrorResponse handleAccountNotFoundException(Exception e) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({UserNotFoundException.class})
  public ErrorResponse handleUserNotFoundException(Exception e) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({CurrencyNotFoundException.class})
  public ErrorResponse handleCurrencyNotFoundException(Exception e) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({AccountAlreadyExistsException.class})
  public ErrorResponse handleAccountAlreadyExistsException(Exception e) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
  }
}
