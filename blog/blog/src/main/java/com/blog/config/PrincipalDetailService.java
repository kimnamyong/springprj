package com.blog.config;

import com.blog.model.User;
import com.blog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrincipalDetailService implements UserDetailsService {
 @Autowired
 private UserRepository userRepository;

 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

  User principal=userRepository.findByUsername(username).orElse(null);
  log.info("principal:"+ principal);

  return new PrincipalDetail(principal);
 }
}
