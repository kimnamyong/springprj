package com.validation1.service;

import com.validation1.dto.Professor;
import com.validation1.dto.Student;
import com.validation1.mapper.ProfessorMapper;
import com.validation1.mapper.StudentMapper;
import com.validation1.model.ProfessorEdit;
import com.validation1.model.StudentEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
 @Autowired
 ProfessorMapper professorMapper;

 public List<Professor> findAll() {
  List<Professor> professors= professorMapper.findAll();
  return professors;
 }

 public Professor findById(String name) {
  Professor professor= professorMapper.findByProfessorName(name);
  return professor;
 }

 public void insert(ProfessorEdit professorEdit) {
  System.out.println("professor1 : " + professorEdit);
  Professor professor = toDto(professorEdit);
  System.out.println("insert : " + professor);
  professorMapper.insert(professor);
 }


 public Professor toDto(ProfessorEdit professorEdit) {
  System.out.println("professorEdit : " + professorEdit);
  Professor professorDto = new Professor();
  professorDto.setId(professorEdit.getId());
  professorDto.setName(professorEdit.getName());
  professorDto.setDepartmentId(professorEdit.getDepartmentId());
  professorDto.setEmail(professorEdit.getEmail());
  professorDto.setPhone(professorEdit.getPhone());
  professorDto.setOffice(professorEdit.getOffice());
  return professorDto;
 }


 public ProfessorEdit findOne(int id) {
  Professor professorDto = professorMapper.findOne(id);

  return toEditModel(professorDto);
 }

 public ProfessorEdit toEditModel(Professor professorDto) {
  ProfessorEdit professorEdit = new ProfessorEdit();
  professorEdit.setId(professorDto.getId());
  professorEdit.setName(professorDto.getName());
  professorEdit.setDepartmentId(professorDto.getDepartmentId());
  professorEdit.setEmail(professorDto.getEmail());
  professorEdit.setPhone(professorDto.getPhone());
  professorEdit.setOffice(professorDto.getOffice());
  return professorEdit;
 }

 public void update(ProfessorEdit professorEdit) {
  Professor professor = toDto(professorEdit);
  professorMapper.update(professor);
 }

 public void delete(int id) {
  professorMapper.delete(id);
 }
}
