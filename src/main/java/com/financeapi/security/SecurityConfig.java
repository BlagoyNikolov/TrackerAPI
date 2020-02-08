package com.financeapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final RestAuthenticationEntryPoint authenticationEntryPoint;

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .cors()
            .and()
        .csrf().disable()
//            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            .ignoringAntMatchers("/api/tracker/auth/register")
//            .and()
        .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/tracker/auth/login", "/api/tracker/auth/register").permitAll()
            .antMatchers("/v2/api-docs",
                "/swagger-resources/configuration/ui",
                "/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/configuration/security",
                "/swagger*/**").permitAll()
            .antMatchers("/",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js")
            .permitAll()
            .anyRequest().authenticated()
            .and()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
        .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint);

    httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
