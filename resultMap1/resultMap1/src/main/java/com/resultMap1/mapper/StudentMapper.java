package com.resultMap1.mapper;


import com.resultMap1.dto.Student;
import com.resultMap1.dto.Sugang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
 @ResultMap("findAll")
 @Select("""
     SELECT s.*, d.departmentName , d.shortName, d.phone departmentPhone
      FROM student s JOIN department d
        ON s.departmentId = d.id
    """)
 List<Student> findAll();
}
