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
@RequestMapping("lecture")
public class LectureController {

 // 의존성주입(dependency injection)
 @Autowired LectureMapper lectureMapper;
 @Autowired ProfessorMapper professorMapper;

 @RequestMapping("list")
 public String list(Model model) {
   model.addAttribute("lectures", lectureMapper.findAll());
//  List<Lecture> lectures = lectureMapper.findAll();
//  for (Lecture lecture : lectures) {
//   Professor professor = professorMapper.findOne(lecture.getProfessorId());
//   lecture.setProfessor(professor);
//  }
//  model.addAttribute("lectures", lectures);
  return "lecture/list";
 }

}
