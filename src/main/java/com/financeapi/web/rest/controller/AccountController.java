package com.financeapi.web.rest.controller;

import com.financeapi.services.AccountService;
import com.financeapi.web.rest.resources.account.AccountRequest;
import com.financeapi.web.rest.resources.account.AccountResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tracker")
public class AccountController {

  private final AccountService accountService;

  @ApiOperation(value = "Retrieves an account")
  @GetMapping("/v1/accounts/{accountId}")
  @ResponseStatus(HttpStatus.OK)
  public AccountResponse retrieveAccount(
      @ApiParam(value = "The id of the existing account")
      @PathVariable Long accountId) {
    return accountService.retrieveAccount(accountId);
  }

  @ApiOperation(value = "Creates a new account")
  @PostMapping("/v1/accounts")
  @ResponseStatus(HttpStatus.CREATED)
  public AccountResponse createAccount(
      @ApiParam(value = "The request containing the payload needed to create a new account")
      @Valid @RequestBody AccountRequest accountRequest) {
    return accountService.createAccount(accountRequest);
  }
}
