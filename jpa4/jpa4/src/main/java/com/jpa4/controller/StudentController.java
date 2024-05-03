package com.jpa4.controller;

import com.jpa4.entity.Department;
import com.jpa4.entity.Student;
import com.jpa4.model.StudentEdit;
import com.jpa4.respository.DepartmentRepository;
import com.jpa4.respository.StudentRepository;
import com.jpa4.service.DepartmentService;
import com.jpa4.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

 @Autowired
 StudentService studentService;
 @Autowired
 DepartmentService departmentService;

 @GetMapping("list")
 public String list(Model model) {
  List<Student> students = studentService.findAll();
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
  if (bindingResult.hasErrors()) {
   model.addAttribute("departments", departmentService.findAll());
   return "student/edit";
  }
  Student student2 = studentService.findByStudentNo(studentEdit.getStudentNo());
  if (student2 != null) {
   bindingResult.rejectValue("studentNo", null, "학번이 중복됩니다.");
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
  if (bindingResult.hasErrors()) {
   model.addAttribute("departments", departmentService.findAll());
   return "student/edit";
  }
  Student student2 = studentService.findByStudentNo(studentEdit.getStudentNo());
  if (student2 != null && student2.getId() != studentEdit.getId()) {
   bindingResult.rejectValue("studentNo", null, "학번이 중복됩니다.");
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


}
