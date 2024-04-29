package com.validation1.service;

import com.validation1.dto.Department;
import com.validation1.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

 @Autowired
 public DepartmentMapper departmentMapper;

 public List<Department> findAll() {
  return departmentMapper.findAll();
 }

}
