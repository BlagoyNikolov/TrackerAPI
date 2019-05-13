package com.financeapi.web.rest.resources.account;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponse extends ResourceSupport {

  private Long accountId;
  private String name;
  private BigDecimal amount;
  private String currency;
  private Long userId;
}
