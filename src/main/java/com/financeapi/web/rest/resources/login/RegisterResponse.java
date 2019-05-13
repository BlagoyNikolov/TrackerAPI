package com.financeapi.web.rest.resources.login;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
public class RegisterResponse extends ResourceSupport {

  private Long userId;

  private String username;

  private String email;
}
