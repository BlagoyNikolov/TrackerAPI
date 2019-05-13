package com.financeapi.services.impl;

import com.financeapi.entities.User;
import com.financeapi.exceptions.InvalidInputException;
import com.financeapi.mappers.UserMapper;
import com.financeapi.repositories.UserRepository;
import com.financeapi.services.UserService;
import com.financeapi.web.rest.resources.login.LoginRequest;
import com.financeapi.web.rest.resources.login.RegisterRequest;
import com.financeapi.web.rest.resources.login.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  public static final String USER_NOT_FOUND = "User not found";
  public static final String INVALID_PASSWORD = "Invalid password";
  public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
  public static final String EMAIL_ALREADY_EXISTS = "Email already exists";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserMapper userMapper;

  @Override
  public void login(LoginRequest loginRequest) throws InvalidInputException {
    User user = userRepository.findByUsername(loginRequest.getUsername())
        .orElseThrow(() -> new InvalidInputException(USER_NOT_FOUND));

    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
      throw new InvalidInputException(INVALID_PASSWORD);
    }
  }

  @Override
  public RegisterResponse register(RegisterRequest registerRequest) throws InvalidInputException {
    validateUsernameExists(registerRequest.getUsername());
    validateEmailExists(registerRequest.getEmail());
    User newUser = persistUser(registerRequest);
    return userMapper.userToRegisterResponse(newUser);
  }

  @Override
  public Optional<User> getUser(Long userId) {
    return userRepository.getByUserId(userId);
  }

  private User persistUser(RegisterRequest registerRequest) {
    String encryptedPassword = passwordEncoder.encode(registerRequest.getPassword());
    User newUser = userMapper.registerRequestToUser(registerRequest);
    newUser.setPassword(encryptedPassword);
    return userRepository.save(newUser);
  }

  private void validateUsernameExists(String username) throws InvalidInputException {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new InvalidInputException(USERNAME_ALREADY_EXISTS);
    }
  }

  private void validateEmailExists(String email) throws InvalidInputException {
    if (userRepository.findByEmail(email).isPresent()) {
      throw new InvalidInputException(EMAIL_ALREADY_EXISTS);
    }
  }

}
