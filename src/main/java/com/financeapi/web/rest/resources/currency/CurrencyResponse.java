package com.financeapi.web.rest.resources.currency;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyResponse {

  private String currencyId;

  private String language;

  private String region;
}
