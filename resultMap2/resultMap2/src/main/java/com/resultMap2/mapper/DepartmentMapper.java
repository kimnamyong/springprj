package com.resultMap2.mapper;

import com.resultMap2.dto.Department;
import com.resultMap2.dto.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper {

 @ResultMap("findAll")
 @Select("""
     SELECT d.*, s.studentNo , s.name, s.phone studentPhone, s.sex, s.email 
      FROM department d JOIN student s
        ON d.id = s.departmentId       
    """)
 List<Department> findAll();

}