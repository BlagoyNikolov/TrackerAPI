package com.financeapi.web.rest.controller;

import com.financeapi.services.CurrencyService;
import com.financeapi.web.rest.resources.currency.CurrencyResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tracker")
public class CurrencyController {

  private final CurrencyService currencyService;

  @ApiOperation(value = "Retrieves all currencies")
  @GetMapping("/v1/currencies")
  @ResponseStatus(HttpStatus.OK)
  public List<CurrencyResponse> retrieveAllCurrencies() {
    return currencyService.getAllCurrencies();
  }

  @ApiOperation(value = "Retrieves a currencies by its id")
  @GetMapping("/v1/currencies/{currencyId}")
  @ResponseStatus(HttpStatus.OK)
  public CurrencyResponse retrieveCurrency(
      @ApiParam(value = "The id of the existing currency")
      @PathVariable String currencyId) {
    return currencyService.getCurrencyById(currencyId);
  }
}
