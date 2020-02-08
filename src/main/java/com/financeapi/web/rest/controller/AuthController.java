package com.financeapi.web.rest.controller;

import com.financeapi.services.AuthenticationService;
import com.financeapi.web.rest.resources.login.LoginRequest;
import com.financeapi.web.rest.resources.login.LoginResponse;
import com.financeapi.web.rest.resources.login.RegisterRequest;
import com.financeapi.web.rest.resources.login.RegisterResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tracker/auth")
public class AuthController {

  private final AuthenticationService authenticationService;

  @ApiOperation(value = "Logs an existing user into the application")
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public LoginResponse login(
      @ApiParam(value = "The login request containing the payload with user details")
      @Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
    return authenticationService.login(loginRequest, response);
  }

  @ApiOperation(value = "Registers a new user into the application")
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public RegisterResponse register(
      @ApiParam(value = "The register request containing the payload with user details")
      @Valid @RequestBody RegisterRequest registerRequest) {
    return authenticationService.register(registerRequest);
  }
}
