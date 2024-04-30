package com.jpa1.repository;

import com.jpa1.entity.Department;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
//import static org.junit.Assert.assertThat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class DepartmentRepositoryTest {
 @Autowired DepartmentRepository departmentRepository;


 @Transactional
 @Test
 @DisplayName("저장테스트")
 public void createTest(){
  Department d=new Department();
  d.setName("건축학과");
  d.setShortName("미술");
  d.setPhone("023335555");
  Department d2=departmentRepository.save(d);
  log.info(String.valueOf(d2));
  String d3="Department(id=16, name=건축학과, shortName=미술, phone=023335555)";
  assertEquals(d3.toString(), d2.toString());
        //  예상,   결과치
 }
 @Test
 @DisplayName("조회테스트")
 public void findByIdTest(){
   Department d1=departmentRepository.findById(1).orElse(null);
  //Optional<Department> d1=departmentRepository.findById(1);
  String d2="Department(id=1, name=컴퓨터공학과, shortName=소프, phone=02-2610-9563)";
  
  log.info(d1.toString());
  // 예상
  
  // 실제
  
  // 검증
  //assertEquals(d2.toString(), d1.toString());


 }
}