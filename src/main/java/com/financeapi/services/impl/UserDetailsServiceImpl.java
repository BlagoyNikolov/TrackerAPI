package com.financeapi.services.impl;

import com.financeapi.entities.User;
import com.financeapi.exceptions.InvalidInputException;
import com.financeapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  public static final String USER_NOT_FOUND = "User not found";

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username).orElseThrow(() -> new InvalidInputException(USER_NOT_FOUND));

    List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
  }
}
