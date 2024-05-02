package com.jpa4.respository;

import com.jpa4.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

 Student findByStudentNo(String studentNo);
 List<Student> findByName(String name);
 List<Student> findByNameStartsWith(String name);
 List<Student> findByDepartmentName(String name);
 List<Student> findByDepartmentNameStartsWith(String name);

 List<Student> findByOrderByName();
 List<Student> findByOrderByNameDesc();
 List<Student> findByDepartmentIdOrderByNameDesc(int id);

}

