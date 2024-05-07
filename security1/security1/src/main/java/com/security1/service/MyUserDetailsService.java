package com.security1.service;

import com.security1.config.MyUserDetails;
import com.security1.dto.User;
import com.security1.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

 private final UserMapper userMapper;
// @Autowired
// UserMapper userMapper;

 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  User user = userMapper.findByLoginName(username);
  if (user == null) throw new UsernameNotFoundException(username);

   UserDetails user2= new MyUserDetails(user);
   return  user2;
  //return new MyUserDetails(user);
 }

}
