package com.jpa4.respository;

import com.jpa4.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

 Student findByStudentNo(String studentNo);
 List<Student> findAllByOrderByStudentNo();
 List<Student> findAllByOrderByIdDesc();

 List<Student> findByName(String name);
 List<Student> findByNameStartsWith(String name);
 List<Student> findByDepartmentName(String name);
 List<Student> findByDepartmentNameStartsWith(String name);

 List<Student> findByOrderByName();
 List<Student> findByOrderByNameDesc();
 List<Student> findByDepartmentIdOrderByNameDesc(int id);

 List<Student> findBySugangsLectureTitle(String title);
 List<Student> findByDepartmentProfessorsName(String name);

 @Query("SELECT s.studentNo, s.name, SIZE(s.sugangs) FROM Student s ORDER BY SIZE(s.sugangs) DESC")
 List<Object[]> findSugangCount();

 @Query("SELECT s FROM Student s JOIN s.sugangs g WHERE g.lecture.title = ?1")
 List<Student> findByLectureTite(String title);

 @Query("""
         SELECT s
         FROM Student s
         WHERE EXISTS (SELECT g.lecture
                       FROM s.sugangs g
                       WHERE g.lecture.professor.id = ?1 OR g.lecture.professor.name = ?2) """)
 List<Student> findByProfessorIdOrProfessorName(int id, String name);

 @Query("""
         SELECT s
         FROM Student s
         WHERE EXISTS (SELECT g.lecture
                        FROM s.sugangs g
                        WHERE g.lecture.professor.id = :id OR g.lecture.professor.name = :name) """)
 List<Student> findByProfessorNameOrProfessorId(String name, int id);


}

