package com.jpa4.respository;

import com.jpa4.entity.Professor;
import com.jpa4.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository  extends JpaRepository<Professor, Integer>
{


}
