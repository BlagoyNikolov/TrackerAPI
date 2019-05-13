package com.financeapi.services;

import com.financeapi.exceptions.account.AccountAlreadyExistsException;
import com.financeapi.exceptions.account.AccountNotFoundException;
import com.financeapi.exceptions.currency.CurrencyNotFoundException;
import com.financeapi.exceptions.user.UserNotFoundException;
import com.financeapi.web.rest.resources.account.AccountRequest;
import com.financeapi.web.rest.resources.account.AccountResponse;

public interface AccountService {

  AccountResponse retrieveAccount(Long accountId) throws AccountNotFoundException;

  AccountResponse createAccount(AccountRequest accountRequest) throws UserNotFoundException, CurrencyNotFoundException, AccountAlreadyExistsException, AccountNotFoundException;

}
