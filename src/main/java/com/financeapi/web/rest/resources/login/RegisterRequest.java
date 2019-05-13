package com.financeapi.web.rest.resources.login;

import com.financeapi.web.rest.validation.PasswordsMatch;
import com.financeapi.web.rest.validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@PasswordsMatch
public class RegisterRequest {

  @NotBlank
  private String username;

  @NotBlank
  private String email;

  @NotBlank
  @ValidPassword
  private String password;

  @NotBlank
  private String confirmPassword;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;
}
