package com.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled=true)
public class SecurityConfig {

 //private final AuthenticationFailureHandler customFailureHandler;

 @Bean
 public PasswordEncoder  passwordEncoder() {
  return new BCryptPasswordEncoder();
 }


 @Bean
 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  http
          .authorizeHttpRequests((requests) -> requests
                  .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
          .csrf((csrf) -> csrf.disable())
          .formLogin((formLogin) -> formLogin
                  .loginPage("/auth/loginForm")
                  .loginProcessingUrl("/auth/loginProc")
                  .failureUrl("/auth/fail")
                  .defaultSuccessUrl("/",true)
                  .usernameParameter("username")
                  .passwordParameter("password")
                  .permitAll())
  ;
  return http.build();
 }

 @Bean
 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)     throws Exception {
  return authenticationConfiguration.getAuthenticationManager();
 }

} //