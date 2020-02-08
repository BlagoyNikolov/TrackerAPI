package com.financeapi.services;

import com.financeapi.web.rest.resources.login.LoginRequest;
import com.financeapi.web.rest.resources.login.LoginResponse;
import com.financeapi.web.rest.resources.login.RegisterRequest;
import com.financeapi.web.rest.resources.login.RegisterResponse;

import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {

  LoginResponse login(LoginRequest loginRequest, HttpServletResponse response);

  RegisterResponse register(RegisterRequest registerRequest);
}
