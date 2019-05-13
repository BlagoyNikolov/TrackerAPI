package com.financeapi.web.rest.resources.account;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest {

  @NotBlank
  private String name;

  @PositiveOrZero
  private BigDecimal amount;

  @NotBlank
  private String currency;

  @Positive
  private Long userId;
}
