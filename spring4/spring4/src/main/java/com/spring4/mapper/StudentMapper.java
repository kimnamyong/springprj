package com.spring4.mapper;


import com.spring4.dto.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
 @Select("""
             SELECT s.*, d.name departmentName
             FROM student s JOIN department d ON s.departmentId = d.id """)
 List<Student> findAll();

}

