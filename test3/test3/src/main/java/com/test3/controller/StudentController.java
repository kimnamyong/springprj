package com.test3.controller;

import com.test3.dto.ActionResult;
import com.test3.entity.Student;
import com.test3.repository.StudentRepository;
import com.test3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

 @Autowired
 StudentService studentService;

 @GetMapping("students")
 public List<Student> students() {
  return studentService.findAll();
 }

 @GetMapping("student/{id}")
 public Student student(@PathVariable("id") int id) {
  return studentService.findById(id);
 }

 @PostMapping("student")
 public ActionResult insert(@RequestBody Student student) {
  try {
   studentService.insert(student);
   return new ActionResult(true);
  } catch (Exception e) {
   return new ActionResult(false, e.getMessage());
  }
 }

 @PutMapping("student")
 public ActionResult update(@RequestBody Student student) {
  studentService.update(student);
  return new ActionResult(true);
 }

 @DeleteMapping("student/{id}")
 public ActionResult delete(@PathVariable("id") int id) {
  studentService.deleteById(id);
  return new ActionResult(true);
 }

}
