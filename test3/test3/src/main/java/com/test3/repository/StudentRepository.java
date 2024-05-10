package com.test3.repository;

import com.test3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student, Integer> {

 Student findByStudentNo(String studentNo);
}
