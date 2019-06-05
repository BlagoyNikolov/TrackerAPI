package com.financeapi.services;

import com.financeapi.entities.User;
import com.financeapi.web.rest.resources.login.LoginRequest;
import com.financeapi.web.rest.resources.login.RegisterRequest;
import com.financeapi.web.rest.resources.login.RegisterResponse;

import java.util.Optional;

public interface UserService {

  void login(LoginRequest loginRequest);

  RegisterResponse register(RegisterRequest registerRequest);

  Optional<User> getUser(Long userId);
}
