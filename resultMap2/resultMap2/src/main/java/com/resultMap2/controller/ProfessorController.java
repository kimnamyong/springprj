package com.resultMap2.controller;


import com.resultMap2.dto.Lecture;
import com.resultMap2.dto.Professor;
import com.resultMap2.mapper.LectureMapper;
import com.resultMap2.mapper.ProfessorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("professor")
public class ProfessorController {

 @Autowired
 LectureMapper lectureMapper;
 @Autowired ProfessorMapper professorMapper;

 @RequestMapping("list")
 public String list(Model model) {
//  List<Professor> professors = professorMapper.findAll();
//  for (Professor professor : professors) {
//   List<Lecture> lectures = lectureMapper.findByProfessorId(professor.getId());
//   professor.setLectures(lectures);
//  }
//  model.addAttribute("professors", professors);
  model.addAttribute("professors", professorMapper.findAll());
  return "professor/list";
 }

}



