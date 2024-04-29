package com.validation1.mapper;

import com.validation1.dto.Professor;
import com.validation1.dto.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProfessorMapper {

 @Select("SELECT * FROM professor WHERE id = #{id}")
 Professor findOne(int id);

 @Select("SELECT * FROM professor WHERE id = #{name}")
 Professor findByProfessorName(String name);

 @Select("""
        SELECT p.*, d.name departmentName
        FROM professor p LEFT JOIN department d ON p.departmentId = d.id """)
 List<Professor> findAll();

 @Insert("""
        INSERT professor (id, name, departmentId, phone, email, office)
        VALUES (#{id}, #{name}, #{departmentId}, #{phone}, #{email}, #{office}) """)
 void insert(Professor professor);
 //@Options(useGeneratedKeys=true, keyProperty="id")


 @Update("""
        UPDATE professor SET
          id= #{id},
          name = #{name},
          departmentId = #{departmentId},
          phone = #{phone},
          email = #{email},
          office = #{office}
        WHERE id = #{id} """)
 void update(Professor professor);

 @Delete("DELETE FROM professor WHERE id = #{id}")
 void delete(int id);
}

