package com.mybatis4.mapper;

import com.mybatis4.dto.Student;
import com.mybatis4.model.Pagination;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

 @Data
 @AllArgsConstructor
 public class Order {
  int value;
  String label;
 }

 // interface에 선언된 변수는 public static final 상수이다.
 Order[] orders = new Order[] {
         new Order(0, "정렬 순서"),
         new Order(1, "학번 오름차순"),
         new Order(2, "학번 내림차순"),
         new Order(3, "이름 오름차순"),
         new Order(4, "학과 오름차순")
 };


 @Select("SELECT * FROM student WHERE id = #{id}")
 Student findOne(int id);

 @Select("SELECT * FROM student WHERE studentNo = #{studentNo}")
 Student findByStudentNo(String studentNo);

@Select("""
            SELECT s.*, d.name departmentName
            FROM student s LEFT JOIN department d ON s.departmentId = d.id
            WHERE #{st} = '' OR
              s.studentNo = #{st} OR
              s.name LIKE CONCAT('%', #{st}, '%') OR
              d.name LIKE CONCAT('%', #{st}, '%')
              ORDER BY
                 (CASE WHEN #{od} = 0 THEN s.id END) ASC,
                 (CASE WHEN #{od} = 1 THEN s.studentNo END) ASC,
                 (CASE WHEN #{od} = 2 THEN s.studentNo END) DESC,
                 (CASE WHEN #{od} = 3 THEN s.name END) ASC,
                 (CASE WHEN #{od} = 4 THEN d.name END) ASC
          
            LIMIT #{firstRecordIndex}, #{sz} """)
List<Student> findAll(Pagination pagination);


 @Select("""
            SELECT COUNT(*)
            FROM student s LEFT JOIN department d ON s.departmentId = d.id
            WHERE #{st} = '' OR
              s.studentNo = #{st} OR
              s.name LIKE CONCAT('%', #{st}, '%') OR
              d.name LIKE CONCAT('%', #{st}, '%') """)
 int getCount(Pagination pagination);




 @Insert("""
        INSERT student (studentNo, name, departmentId, phone, sex, email)
        VALUES (#{studentNo}, #{name}, #{departmentId}, #{phone}, #{sex}, #{email}) """)
 @Options(useGeneratedKeys=true, keyProperty="id")
 void insert(Student student);

 @Update("""
        UPDATE student SET
          studentNo= #{studentNo},
          name = #{name},
          departmentId = #{departmentId},
          phone = #{phone},
          sex = #{sex},
          email = #{email}
        WHERE id = #{id} """)
 void update(Student student);

 @Delete("DELETE FROM student WHERE id = #{id}")
 void delete(int id);
}

