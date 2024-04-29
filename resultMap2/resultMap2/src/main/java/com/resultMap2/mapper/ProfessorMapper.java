package com.resultMap2.mapper;

import com.resultMap2.dto.Professor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProfessorMapper {

// @ResultMap("findAll")
// @Select("""
//      SELECT p.*, l.id lectureId, l.title, l.year, l.semester, l.room
//      FROM professor p JOIN lecture l
//        ON p.id = l.professorId """)
// List<Professor> findAll();
@Results({
        @Result(property="id", column="id", id=true),
        @Result(property="lectures", column="id",
                many=@Many(select="com.resultMap2.mapper.LectureMapper.findByProfessorId"))
})
 @Select("SELECT * FROM professor")
 List<Professor> findAll();

 @Select("SELECT * FROM professor WHERE id = #{id}")
 Professor findOne(int id);  // findById(int id)

}

