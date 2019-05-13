package com.financeapi.web.rest.validation;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;

public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {

  @Override
  public void initialize(ValidPassword constraintAnnotation) {
  }

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {

    if (StringUtils.isEmpty(password)) {
      return false;
    }

    PasswordValidator validator = new PasswordValidator(
          new LengthRule(3, 8),
          new CharacterRule(EnglishCharacterData.UpperCase, 1),
          new CharacterRule(EnglishCharacterData.LowerCase, 1),
          new CharacterRule(EnglishCharacterData.Digit, 1),
          new CharacterRule(EnglishCharacterData.Special, 1),
          new WhitespaceRule()
    );

    RuleResult result = validator.validate(new PasswordData(password));
    if (result.isValid()) {
      return true;
    }

    String messageTemplate = validator.getMessages(result)
        .stream()
        .collect(Collectors.joining(" "));
    context.buildConstraintViolationWithTemplate(messageTemplate)
        .addConstraintViolation()
        .disableDefaultConstraintViolation();

    return false;
  }
}
