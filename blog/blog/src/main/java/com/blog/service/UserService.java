package com.blog.service;

import com.blog.model.Board;
import com.blog.model.User;
import com.blog.repository.BoardRepository;
import com.blog.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserService {

 @Autowired
 private UserRepository userRepository;

 @Autowired
 private BoardRepository boardRepository;


 @Autowired
 HttpSession session;

 @Transactional
 public int 회원가입(User user) {
  try {
   userRepository.save(user);
   return 1;
  }catch (Exception e){
   e.printStackTrace();
  }
  return -1;
 }

 @Transactional(readOnly = true)
 public User 로그인(User user) {
     User principal= userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

     return principal;
 }

 public int 중복확인(String username) {
  User user=userRepository.findByUsername(username);
  if(user==null) return 1;
    else return 0;
 }

 @Transactional
 public void 회원수정(User user) {
  User persistance=userRepository.findById(user.getId()).orElse(null);
  persistance.setPassword(user.getPassword());
  persistance.setEmail(user.getEmail());

  session.setAttribute("principal",persistance);
 }

 @Transactional
 public int 회원탈퇴(Integer id, User user) {
    User user2=userRepository.findById(id).orElse(null);

    if(user2 != null){
      User user3 = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
      if(user3 != null) {
       boardRepository.deleteByUserId(user3.getId());
       userRepository.deleteById(id);
       return 1;
      }
    }
    return -1;
 }
}
