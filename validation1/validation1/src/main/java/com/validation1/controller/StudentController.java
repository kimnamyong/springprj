package com.validation1.controller;


import com.validation1.dto.Department;
import com.validation1.dto.Student;
import com.validation1.model.StudentEdit;
import com.validation1.service.DepartmentService;
import com.validation1.service.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {
 @Autowired
 StudentService studentService;

 @Autowired
 DepartmentService departmentService;

 @GetMapping("list")
 public String list(Model model) {
   List<Student> students= studentService.findAll();

  model.addAttribute("students", students);
  return "student/list";
 }
 @GetMapping("create")
 public String create(Model model) {
  StudentEdit studentEdit = new StudentEdit();
  List<Department> departments = departmentService.findAll();
  model.addAttribute("studentEdit", studentEdit);
  model.addAttribute("departments", departments);
  return "student/edit";
 }

 @PostMapping("create")
 public String create(Model model,
                      @Valid StudentEdit studentEdit, BindingResult bindingResult) {

  if (studentService.hasErrors(studentEdit, bindingResult)) {
   log.info("bindResult : {}" , bindingResult.getAllErrors());
   model.addAttribute("departments", departmentService.findAll());
   return "student/edit";
  }

  studentService.insert(studentEdit);

  return "redirect:list";
 }

 @GetMapping("edit")
 public String edit(Model model, int id) {
  StudentEdit studentEdit = studentService.findOne(id);
  List<Department> departments = departmentService.findAll();
  model.addAttribute("studentEdit", studentEdit);
  model.addAttribute("departments", departments);
  return "student/edit";
 }

 @PostMapping("edit")
 public String edit(Model model,
                    @Valid StudentEdit studentEdit, BindingResult bindingResult) {
  if (studentService.hasErrors(studentEdit, bindingResult)) {

   log.info("bindResult : {}" , bindingResult);

   model.addAttribute("departments", departmentService.findAll());
   return "student/edit";
  }
  studentService.update(studentEdit);
  return "redirect:list";
 }

 @GetMapping("delete")
 public String delete(Model model, int id) {
  studentService.delete(id);
  return "redirect:list";
 }

} //
