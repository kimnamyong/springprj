package com.spring4.controller;

import com.spring4.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

 @Autowired
 StudentMapper studentMapper;  // DI

 @RequestMapping("student/list")
 public String list(Model model) {
  model.addAttribute("students", studentMapper.findAll());
  return "student/list";
 }



}
