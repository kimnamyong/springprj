package com.blog.controller;

import com.blog.dto.UserForm;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

 @Autowired // 의존성 주입(DI)
 private UserRepository userRepository;

 @Autowired
 HttpSession session;

 @GetMapping("/home")
 public String home(){
  return "users/join";
 }

 @PostMapping("/home/join")
 //public String join(UserForm form){
 public String join(User user){
 //  User user= form.toEntity();
  User saved = userRepository.save(user);
  return "redirect:/home/user/" + saved.getId();
 }

 @GetMapping("/home/user/{id}")
 public String detail(@PathVariable Integer id, Model model){

  User userEntity=userRepository.findById(id).orElseThrow(()->{
   return new IllegalArgumentException("해당유저는 없습니다. id: " + id);
  });
   model.addAttribute("user", userEntity);
  return "users/ushow";
 }

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용

// 그냥 주소가 / 이면 index.jsp 허용

 // static 이하에 있는 /js/**, /css/**, /image/**
  @GetMapping("/auth/joinForm")
  public String joinForm(){
   return "user/joinForm";
  }

  @GetMapping("/auth/loginForm")
  public String loginForm(){
   return "user/loginForm";
  }

 // logout
// @GetMapping("/user/logout")
// public String logOut(HttpSession session){
//  session.invalidate();
//  return "/user/loginForm";
// }

  @GetMapping("/user/updateForm")
  public String updateForm(Model model){

   model.addAttribute("principal",session.getAttribute("principal"));

   return "user/updateForm";
  }

  // 회원탈퇴
  @GetMapping("/user/deleteForm")
  public String deleteForm(Model model){

   model.addAttribute("principal",session.getAttribute("principal"));

   return "user/deleteForm";
  }


}  // end
