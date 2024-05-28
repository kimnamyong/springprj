package com.shop2.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

 @Override
 public Optional<String> getCurrentAuditor() {
  // 사용자 정보를 조회한다.
  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  String userId = "";
  if(authentication != null){
   userId = authentication.getName();
  }
  return Optional.of(userId);
 }
}
