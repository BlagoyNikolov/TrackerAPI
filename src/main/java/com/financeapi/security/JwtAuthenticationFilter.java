package com.financeapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String JWT_TOKEN = "JWT-TOKEN";

  private final UserDetailsService userDetailService;

  private final TokenUtils tokenUtils;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    String jwtToken = extractTokenFromCookies(request);

    String username;

    if (StringUtils.hasText(jwtToken)) {
      username = tokenUtils.getUsernameFromToken(jwtToken);
      UserDetails userDetails = userDetailService.loadUserByUsername(username);

      if (tokenUtils.isTokenValid(jwtToken, userDetails)) {
        authenticateUser(request, userDetails);
      }
    }

    filterChain.doFilter(request, response);
  }

  private String extractTokenFromCookies(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (request.getCookies() == null) {
      return null;
    }

    return Arrays.stream(cookies)
                 .filter(cookie -> JWT_TOKEN.equals(cookie.getName()))
                 .findFirst()
                 .map(Cookie::getValue)
                 .orElse(null);
  }

  private void authenticateUser(HttpServletRequest request, UserDetails userDetails) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
  }
}
