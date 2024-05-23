package com.blog.service;

import com.blog.model.Board;
import com.blog.model.RoleType;
import com.blog.model.User;
import com.blog.repository.BoardRepository;
import com.blog.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@Service
//@RequiredArgsConstructor
public class UserService {

 @Autowired
 private UserRepository userRepository;

 @Autowired
 private BoardRepository boardRepository;

 @Autowired
 private PasswordEncoder  encoder;

 @Autowired
 HttpSession session;

 //private final PasswordEncoder encoder;


// @Transactional
// public int 회원가입(User user) {
//  try {
//   userRepository.save(user);
//   return 1;
//  }catch (Exception e){
//   e.printStackTrace();
//  }
//  return -1;
// }

 @Transactional
 public int 회원가입(User user) {
  try {

   String rawPassowrd=user.getPassword();
   String encPassowrd=encoder.encode(rawPassowrd);  // 해쉬

   user.setPassword(encPassowrd);
   user.setRole(RoleType.USER);
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

  Optional<User> user=userRepository.findByUsername(username);
  log.info("user:" + user);

  if(user.isPresent()==false) return 1;
    else return 0;

 }


 @Transactional
 public void 회원수정(User user) {

  User persistance=userRepository.findById(user.getId()).orElse(null);

  String rawPassword=user.getPassword();
  String encPassword=encoder.encode(rawPassword); // 패스워드 암호화

  persistance.setPassword(encPassword);
  persistance.setEmail(user.getEmail());

  log.info("사용자"+persistance.toString());

  userRepository.save(persistance);
 }

 @Transactional
 public int 회원탈퇴(Integer id, User user) {
    User user2=userRepository.findById(id).orElse(null);

    if(user2 != null){
     String realPassword=user2.getPassword();
     String checkPassword=user.getPassword();

     // 암호화한 패스워드를 비교한다.
     boolean matches=encoder.matches(checkPassword,realPassword);

      if(matches){
       boardRepository.deleteByUserId(user2.getId());
       userRepository.deleteById(id);
       return 1;
      }
    }
    return -1;
 }
}
