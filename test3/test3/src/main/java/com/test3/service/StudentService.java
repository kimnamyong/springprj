package com.test3.service;

import com.test3.entity.Student;
import com.test3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

 @Autowired
 StudentRepository studentRepository;

 public List<Student> findAll() {
  return studentRepository.findAll();
 }

 public Student findById(int id) {
  return studentRepository.findById(id).get();
 }

 public void insert(Student student) throws Exception {
  if (studentRepository.findByStudentNo(student.getStudentNo()) != null)
   throw new Exception("학번 중복");
  studentRepository.save(student);
 }

 public void update(Student student) {
  studentRepository.save(student);
 }

 public void deleteById(int id) {
  studentRepository.deleteById(id);
 }

}
