package com.thymeleaf2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/thyme")
public class ThymeEx01 {

 @GetMapping(value="ex01")
 public String thymeEx01(Model model){
  model.addAttribute("data","타임리프 예제입니다");

  return "thyme/ex01" ;
 }

}
// http://localhost:8080/thyme/ex01