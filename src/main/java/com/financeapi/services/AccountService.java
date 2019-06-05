package com.financeapi.services;

import com.financeapi.web.rest.resources.account.AccountRequest;
import com.financeapi.web.rest.resources.account.AccountResponse;

public interface AccountService {

  AccountResponse retrieveAccount(Long accountId);

  AccountResponse createAccount(AccountRequest accountRequest);

}
