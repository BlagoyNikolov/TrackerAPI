package com.financeapi.exceptions;

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
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({InvalidInputException.class})
  public ErrorResponse handleInvalidInputException(Exception e) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), e.getMessage());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({AccountNotFoundException.class})
  public ErrorResponse handleAccountNotFoundException(Exception e) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({UserNotFoundException.class})
  public ErrorResponse handleUserNotFoundException(Exception e) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({CurrencyNotFoundException.class})
  public ErrorResponse handleCurrencyNotFoundException(Exception e) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage());
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler({AccountAlreadyExistsException.class})
  public ErrorResponse handleAccountAlreadyExistsException(Exception e) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name(), e.getMessage());
  }
}
