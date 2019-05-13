package com.financeapi.mappers;

import com.financeapi.entities.Account;
import com.financeapi.web.rest.resources.account.AccountResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface AccountMapper {

  @Mappings({
      @Mapping(target = "currency", source = "account.currency.currencyId"),
      @Mapping(target = "userId", source = "account.user.userId")
  })
  AccountResponse accountToAccountResponse(Account account);
}
