package com.financeapi.services;

import com.financeapi.entities.User;
import com.financeapi.exceptions.InvalidInputException;
import com.financeapi.web.rest.resources.login.LoginRequest;
import com.financeapi.web.rest.resources.login.RegisterRequest;
import com.financeapi.web.rest.resources.login.RegisterResponse;

import java.util.Optional;

public interface UserService {

  void login(LoginRequest loginRequest) throws InvalidInputException;

  RegisterResponse register(RegisterRequest registerRequest) throws InvalidInputException;

  Optional<User> getUser(Long userId);
}
