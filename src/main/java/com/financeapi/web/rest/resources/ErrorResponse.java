package com.financeapi.web.rest.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
  private int statusCode;
  private String statusName;
  private String message;
}
