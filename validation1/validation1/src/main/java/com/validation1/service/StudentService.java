package com.validation1.service;

import com.validation1.dto.Student;
import com.validation1.mapper.StudentMapper;
import com.validation1.model.StudentEdit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class StudentService {

 @Autowired
 StudentMapper studentMapper;

 ModelMapper modelMapper=new ModelMapper();

 public List<Student> findAll() {
  List<Student> students= studentMapper.findAll();
   return students;
 }

 public Student findByStudentNo(String studentNo) {
  Student student= studentMapper.findByStudentNo(studentNo);
  return student;
 }

 public void insert(StudentEdit studentEdit) {
  Student student = toDto(studentEdit);
  studentMapper.insert(student);
 }

 public Student toDto(StudentEdit studentEdit) {
//  Student studentDto = new Student();
//  studentDto.setId(studentEdit.getId());
//  studentDto.setStudentNo(studentEdit.getStudentNo());
//  studentDto.setName(studentEdit.getName());
//  studentDto.setDepartmentId(studentEdit.getDepartmentId());
//  studentDto.setEmail(studentEdit.getEmail());
//  studentDto.setPhone(studentEdit.getPhone());
//  studentDto.setSex(studentEdit.getSex());
  return modelMapper.map(studentEdit, Student.class);
 }


 public StudentEdit findOne(int id) {
  Student studentDto = studentMapper.findOne(id);
  return toEditModel(studentDto);
 }

 public StudentEdit toEditModel(Student studentDto) {
//  StudentEdit studentEdit = new StudentEdit();
//  studentEdit.setId(studentDto.getId());
//  studentEdit.setStudentNo(studentDto.getStudentNo());
//  studentEdit.setName(studentDto.getName());
//  studentEdit.setDepartmentId(studentDto.getDepartmentId());
//  studentEdit.setEmail(studentDto.getEmail());
//  studentEdit.setPhone(studentDto.getPhone());
//  studentEdit.setSex(studentDto.getSex());
  return modelMapper.map(studentDto, StudentEdit.class);
 }

 public void update(StudentEdit studentEdit) {
  Student student = toDto(studentEdit);
  studentMapper.update(student);
 }

 public void delete(int id) {
  studentMapper.delete(id);
 }

 public boolean hasErrors(StudentEdit studentEdit, BindingResult bindingResult) {
  if (bindingResult.hasErrors()) return true;

  Student student2 = findByStudentNo(studentEdit.getStudentNo());

  if (student2 != null && student2.getId() != studentEdit.getId()) {
   bindingResult.rejectValue("studentNo", null, "학번이 중복됩니다.");
   return true;
  }
  return false;
 }


}
