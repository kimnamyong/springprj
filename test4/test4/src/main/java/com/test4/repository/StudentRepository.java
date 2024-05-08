package com.test4.repository;

import com.test4.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student, Integer> {

 Student findByStudentNo(String studentNo);
}
