package com.financeapi.services.impl;

import com.financeapi.exceptions.InvalidInputException;
import com.financeapi.security.TokenUtils;
import com.financeapi.services.AuthenticationService;
import com.financeapi.services.UserService;
import com.financeapi.web.rest.resources.login.LoginRequest;
import com.financeapi.web.rest.resources.login.LoginResponse;
import com.financeapi.web.rest.resources.login.RegisterRequest;
import com.financeapi.web.rest.resources.login.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private static final String INVALID_PASSWORD = "Invalid password";
  private static final String JWT_TOKEN = "JWT-TOKEN";
  private static final int COOKIE_EXPIRATION_TIME = 7 * 24 * 60 * 60;

  private final PasswordEncoder passwordEncoder;

  private final UserDetailsService userDetailService;

  private final UserService userService;

  private final AuthenticationManager authenticationManager;

  private final TokenUtils tokenUtils;

  @Override
  public LoginResponse login(LoginRequest loginRequest, HttpServletResponse response) {
    UserDetails user = userDetailService.loadUserByUsername(loginRequest.getUsername());

    validateRequest(loginRequest, user);

    String token = tokenUtils.generateToken(user);

    addJwtCookieToResponse(token, response);

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    return new LoginResponse(token);
  }

  @Override
  public RegisterResponse register(RegisterRequest registerRequest) {
    return userService.createUser(registerRequest);
  }

  private void validateRequest(LoginRequest loginRequest, UserDetails userDetails) {
    if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
      throw new InvalidInputException(INVALID_PASSWORD);
    }
  }

  private void addJwtCookieToResponse(String token, HttpServletResponse response) {
    Cookie cookie = new Cookie(JWT_TOKEN, token);
    cookie.setHttpOnly(true);
    cookie.setMaxAge(COOKIE_EXPIRATION_TIME);
    cookie.setPath("/");
    response.addCookie(cookie);
  }

}
