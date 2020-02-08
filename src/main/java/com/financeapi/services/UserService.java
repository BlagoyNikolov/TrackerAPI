package com.financeapi.services;

import com.financeapi.entities.User;
import com.financeapi.web.rest.resources.login.RegisterRequest;
import com.financeapi.web.rest.resources.login.RegisterResponse;

import java.util.Optional;

public interface UserService {

  Optional<User> getUser(Long userId);

  RegisterResponse createUser(RegisterRequest registerRequest);
}
