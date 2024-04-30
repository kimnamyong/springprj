package com.jpa1.controller;

import com.jpa1.entity.Department;
import com.jpa1.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

 @Autowired
 DepartmentRepository departmentRepository;

 @GetMapping("departments")
 public List<Department> departments() {
  return departmentRepository.findAll();
 }

 @GetMapping("department/{id}")
 public Department department(@PathVariable("id") int id) {
  //return departmentRepository.findById(id).get();
  //return departmentRepository.findById(id).orElse(null);
  return departmentRepository.findById(id).orElseThrow(()->{
   return  new IllegalArgumentException("해당유저가 없습니다. " +id);
  });
//  return departmentRepository.findById(id).orElseThrow(
//          EntityNotFoundException::new
//  );

 }

 @PostMapping("department")
 public boolean insert(@RequestBody Department department) {
  departmentRepository.save(department);
  return true;
 }

 @PutMapping("department")
 public boolean update(@RequestBody Department department) {
  departmentRepository.save(department);
  return true;
 }

 @DeleteMapping("department/{id}")
 public boolean delete(@PathVariable("id") int id) {
  departmentRepository.deleteById(id);
  return true;
 }

}
