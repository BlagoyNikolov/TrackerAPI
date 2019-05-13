package com.financeapi.web.rest.validation;

import com.financeapi.web.rest.resources.login.RegisterRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, RegisterRequest> {

  @Override
  public void initialize(PasswordsMatch constraintAnnotation) {
  }

  @Override
  public boolean isValid(RegisterRequest registerRequest, ConstraintValidatorContext context) {
    return registerRequest.getPassword().equals(registerRequest.getConfirmPassword());
  }
}
