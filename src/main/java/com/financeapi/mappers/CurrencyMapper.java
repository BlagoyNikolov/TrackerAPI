package com.financeapi.mappers;

import com.financeapi.entities.Currency;
import com.financeapi.web.rest.resources.currency.CurrencyResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface CurrencyMapper {

  CurrencyResponse currencyToCurrencyResponse(Currency currency);
}
