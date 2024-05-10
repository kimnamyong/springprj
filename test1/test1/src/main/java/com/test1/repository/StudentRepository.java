package com.test1.repository;

import com.test1.dto.Student;

import java.util.List;
import java.util.Map;

public interface StudentRepository{
 Student findByStudentNo(String number);

 void deleteById(int id);

 List<Student> findAll();

 Map<Object, Object> findById(int id);
}
