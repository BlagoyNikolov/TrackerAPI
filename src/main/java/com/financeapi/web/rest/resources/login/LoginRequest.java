package com.financeapi.web.rest.resources.login;

import com.financeapi.web.rest.validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

  @NotBlank
  private String username;

  @NotBlank
  @ValidPassword
  private String password;
}
