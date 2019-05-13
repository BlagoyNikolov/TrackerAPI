package com.financeapi.mappers;

import com.financeapi.entities.User;
import com.financeapi.web.rest.resources.login.RegisterRequest;
import com.financeapi.web.rest.resources.login.RegisterResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {

  User registerRequestToUser(RegisterRequest registerRequest);

  RegisterResponse userToRegisterResponse(User user);
}
