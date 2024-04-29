package com.resultMap2.controller;

import com.resultMap2.dto.Department;
import com.resultMap2.mapper.DepartmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("department")
public class DepartmentController {

 @Autowired
 DepartmentMapper departmentMapper;

 @RequestMapping("list")
 public String list(Model model) {
  List<Department> departments= departmentMapper.findAll();
  model.addAttribute("departments", departments);
  //log.info(departments);
  System.out.println(departments);
  return "department/list";
 }
}