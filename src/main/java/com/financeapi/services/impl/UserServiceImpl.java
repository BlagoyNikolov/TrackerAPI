package com.financeapi.services.impl;

import com.financeapi.entities.User;
import com.financeapi.exceptions.InvalidInputException;
import com.financeapi.mappers.UserMapper;
import com.financeapi.repositories.UserRepository;
import com.financeapi.services.UserService;
import com.financeapi.web.rest.resources.login.RegisterRequest;
import com.financeapi.web.rest.resources.login.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  public static final String USER_NOT_FOUND = "User not found";
  public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
  public static final String EMAIL_ALREADY_EXISTS = "Email already exists";

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  @Override
  public Optional<User> getUser(Long userId) {
    return userRepository.getByUserId(userId);
  }

  @Override
  public RegisterResponse createUser(RegisterRequest registerRequest) {
    validateUsernameExists(registerRequest.getUsername());
    validateEmailExists(registerRequest.getEmail());
    User newUser = persistUser(registerRequest);
    return userMapper.userToRegisterResponse(newUser);
  }

  private void validateUsernameExists(String username) {
    userRepository.findByUsername(username).ifPresent(u -> {
      throw new InvalidInputException(USERNAME_ALREADY_EXISTS);
    });
  }

  private void validateEmailExists(String email) {
    userRepository.findByEmail(email).ifPresent(e -> {
      throw new InvalidInputException(EMAIL_ALREADY_EXISTS);
    });
  }

  private User persistUser(RegisterRequest registerRequest) {
    String encryptedPassword = passwordEncoder.encode(registerRequest.getPassword());
    User newUser = userMapper.registerRequestToUser(registerRequest);
    newUser.setPassword(encryptedPassword);
    return userRepository.save(newUser);
  }
}
