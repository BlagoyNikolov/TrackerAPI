package com.financeapi.web.rest.controller;

import com.financeapi.exceptions.InvalidInputException;
import com.financeapi.services.UserService;
import com.financeapi.web.rest.resources.login.LoginRequest;
import com.financeapi.web.rest.resources.login.RegisterRequest;
import com.financeapi.web.rest.resources.login.RegisterResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tracker")
public class UserController {

  @Autowired
  private UserService userService;

  @ApiOperation(value = "Logs an existing user into the application")
  @PostMapping("/v1/login")
  @ResponseStatus(HttpStatus.OK)
  public void login(
      @ApiParam(value = "The login request containing the payload with user details")
      @Valid @RequestBody LoginRequest loginRequest
  ) throws InvalidInputException {
    userService.login(loginRequest);
  }

  @ApiOperation(value = "Registers a new user into the application")
  @PostMapping("/v1/register")
  @ResponseStatus(HttpStatus.CREATED)
  public RegisterResponse register(
      @ApiParam(value = "The register request containing the payload with user details")
      @Valid @RequestBody RegisterRequest registerRequest
  ) throws InvalidInputException {
    return userService.register(registerRequest);
  }
}
