package com.financeapi.services.impl;

import com.financeapi.entities.Account;
import com.financeapi.entities.Currency;
import com.financeapi.entities.User;
import com.financeapi.exceptions.account.AccountAlreadyExistsException;
import com.financeapi.exceptions.account.AccountNotFoundException;
import com.financeapi.exceptions.currency.CurrencyNotFoundException;
import com.financeapi.exceptions.user.UserNotFoundException;
import com.financeapi.mappers.AccountMapper;
import com.financeapi.repositories.AccountRepository;
import com.financeapi.services.AccountService;
import com.financeapi.services.CurrencyService;
import com.financeapi.services.UserService;
import com.financeapi.web.rest.controller.AccountController;
import com.financeapi.web.rest.resources.account.AccountRequest;
import com.financeapi.web.rest.resources.account.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
  public static final String ACCOUNT_NOT_FOUND = "Account not found for the given user id";
  public static final String USER_NOT_FOUND = "User not found for the given user id";
  public static final String CURRENCY_NOT_FOUND = "Currency not found for the given currency id";
  public static final String ACCOUNT_ALREADY_EXISTS = "This account already exists for this user";

  private final AccountRepository accountRepository;

  private final UserService userService;

  private final CurrencyService currencyService;

  private final AccountMapper accountMapper;

  @Override
  public AccountResponse retrieveAccount(Long accountId) {
    Account account = accountRepository.findByAccountId(accountId)
        .orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND));
    AccountResponse accountResponse = accountMapper.accountToAccountResponse(account);
    accountResponse.add(linkTo(methodOn(AccountController.class).retrieveAccount(accountId)).withSelfRel());
    return accountResponse;
  }

  @Override
  public AccountResponse createAccount(AccountRequest accountRequest) {
    validateForExistingAccount(accountRequest);
    User user = userService.getUser(accountRequest.getUserId())
        .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    Currency currency = currencyService.getCurrency(accountRequest.getCurrency())
        .orElseThrow(() -> new CurrencyNotFoundException(CURRENCY_NOT_FOUND));

    Account account = persistAccount(accountRequest, user, currency);
    createInitialAccountTransaction(accountRequest);

    AccountResponse accountResponse = accountMapper.accountToAccountResponse(account);
    accountResponse.add(linkTo(methodOn(AccountController.class).retrieveAccount(accountResponse.getAccountId())).withSelfRel());
    return accountResponse;
  }

  private void validateForExistingAccount(AccountRequest accountRequest) {
    boolean accountExists = accountRepository.existsByUserUserIdAndName(accountRequest.getUserId(), accountRequest.getName());
    if (accountExists) {
      throw new AccountAlreadyExistsException(ACCOUNT_ALREADY_EXISTS);
    }
  }

  private Account persistAccount(AccountRequest accountRequest, User user, Currency currency) {
    Account account = Account.builder()
        .name(accountRequest.getName())
        .amount(accountRequest.getAmount())
        .currency(currency)
        .user(user)
        .build();
    return accountRepository.save(account);
  }

  private void createInitialAccountTransaction(AccountRequest accountRequest) {

  }
}
