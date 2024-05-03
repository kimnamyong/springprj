package com.jpa4.service;

import com.jpa4.entity.Department;
import com.jpa4.respository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

 @Autowired
 public DepartmentRepository departmentRepository;

 public List<Department> findAll() {
  return departmentRepository.findAll();
 }

}

