package com.example.first.controller;

import com.example.first.dto.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ThirdController {

 @GetMapping("third/test1")
 public String test1(Model model){

  log.info("third/test1 시작함...");

  List<Student> studentList= new ArrayList<>();

  Student s1=new Student(1,"20001010","이순신","admin1@daum.net");
  Student s2=new Student(2,"20001210","강감찬","admin2@daum.net");
  Student s3=new Student(3,"20001011","이방원","admin3@daum.net");
  Student s4=new Student(4,"20001012","원균","admin4@daum.net");
  Student s5=new Student(5,"20001014","장돈건","admin5@daum.net");
  studentList.add(s1);
  studentList.add(s2);
  studentList.add(s3);
  studentList.add(s4);
  studentList.add(s5);

  model.addAttribute("studentList", studentList);
  return "third/test1";
 }

}
