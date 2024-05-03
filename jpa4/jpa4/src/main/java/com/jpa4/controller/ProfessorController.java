package com.jpa4.controller;

import com.jpa4.entity.Department;
import com.jpa4.entity.Professor;
import com.jpa4.respository.DepartmentRepository;
import com.jpa4.respository.ProfessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("professor")
public class ProfessorController {

 @Autowired
 ProfessorRepository professorRepository;
 @Autowired
 DepartmentRepository departmentRepository;

 @GetMapping("list")
 public String list(Model model) {
  List<Professor> professors = professorRepository.findAll();
  model.addAttribute("professors", professors);
  return "professor/list";
 }

 @GetMapping("create")
 public String create(Model model) {
  Professor professor = new Professor();
  List<Department> departments = departmentRepository.findAll();
  model.addAttribute("professor", professor);
  model.addAttribute("departments", departments);
  return "professor/edit";
 }

 @PostMapping("create")
 public String create(Model model, Professor professor) {
  professorRepository.save(professor);
  return "redirect:list";
 }

 @GetMapping("edit")
 public String edit(Model model, int id) {
  Professor professor = professorRepository.findById(id).get();
  List<Department> departments = departmentRepository.findAll();
  model.addAttribute("professor", professor);
  model.addAttribute("departments", departments);
  return "professor/edit";
 }

 @PostMapping("edit")
 public String edit(Model model, Professor professor) {

  log.info("professor " + professor);

  professorRepository.save(professor);
  return "redirect:list";
 }

 @GetMapping("delete")
 public String delete(Model model, int id) {
  professorRepository.deleteById(id);
  return "redirect:list";
 }

}
