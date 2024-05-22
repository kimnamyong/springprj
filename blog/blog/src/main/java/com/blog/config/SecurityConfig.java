package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled=true)
public class SecurityConfig {


 @Bean
 public PasswordEncoder passwordEncoder() {
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
                  .defaultSuccessUrl("/")
                  .permitAll())
  ;
  return http.build();
 }


}