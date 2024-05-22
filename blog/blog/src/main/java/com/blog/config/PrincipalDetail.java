package com.blog.config;

import com.blog.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
public class PrincipalDetail implements UserDetails {

 private User user;  //컴포지션

 public PrincipalDetail(User user) {
  this.user = user;
 }


 @Override
 public Collection<? extends GrantedAuthority> getAuthorities() {
  Collection<GrantedAuthority> collectors=new ArrayList<>();
  collectors.add(() -> {
   return "ROLE_"+user.getRole();  //스프링롤규칙  ROLE_USER,필수사항
  });
  return collectors;
 }

 @Override
 public String getPassword() {
  return user.getPassword();
 }

 @Override
 public String getUsername() {
  return user.getUsername();
 }

 @Override
 public boolean isAccountNonExpired() {
  return true;
 }

 @Override
 public boolean isAccountNonLocked() {
  return true; // 잠기지 않음
 }

 @Override
 public boolean isCredentialsNonExpired() {
  return true;
 }

 @Override
 public boolean isEnabled() {
  return true;
 }
}
