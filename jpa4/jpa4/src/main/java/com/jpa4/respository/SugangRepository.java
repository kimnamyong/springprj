package com.jpa4.respository;

import com.jpa4.entity.Sugang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SugangRepository extends JpaRepository<Sugang, Integer> {

 List<Sugang> findByLectureIdOrderByGradeDesc(int lectureId);

 @Modifying(clearAutomatically = true, flushAutomatically = true)
 @Query(""" 
          UPDATE Sugang g SET g.grade = :after 
          WHERE g.lecture.id = :lectureId AND g.grade = :before """)
 void udateGradeByLectureId(int lectureId, String before, String after);

}
