package com.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled=true)
public class SecurityConfig {
 @Bean
 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  http.authorizeHttpRequests((requests) -> requests
                  .requestMatchers("/admin/**").hasRole("ADMIN")
                  .requestMatchers("/professor/**").hasRole("PROFESSOR")
                  .requestMatchers("/user/**","/admin/**","/professor/**").authenticated()
                  .anyRequest().permitAll()
          )
          .formLogin((form) -> form
                  .loginPage("/login")
                  .loginProcessingUrl("/login_processing")
                  .failureUrl("/login?error")
                  .defaultSuccessUrl("/user/redirect", true)
                  .usernameParameter("loginName")
                  .passwordParameter("passwd")
                  .permitAll()
          )
          .logout((logout) -> logout
                  .logoutRequestMatcher(new AntPathRequestMatcher("/logout_processing"))
                  .logoutSuccessUrl("/login")
                  .invalidateHttpSession(true)
                  .permitAll())

  ;


  return http.build();
 }


 // false 는 보안컨텍스를 유지하지 않는다. 로그인상태유지
 // true 는 보안컨텍스를 유지한다.

 @Bean
 PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
 }
}


