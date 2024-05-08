package com.test4.controller;

import com.test4.entity.Student;
import com.test4.repository.StudentRepository;
import com.test4.service.StudentService;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class) // 테스트할 컨트롤러 클래스를 명시해야 함.
class StudentControllerTest {
 // 서버 URL을 요청하기 위한 클라이언트 객체
 // 마치 웹브라우저가 서버 URL을 요청하는 것 처럼,
 // 테스트 코드가 서버 URL을 요청하기 위해 사용할 객체
 @Autowired
 WebTestClient webClient;

 // 서버가 출력한 데이터가 맞는지 확인하기 위해
 // DB에서 데이터를 조회해야 한다.
 @Autowired StudentRepository studentRepository;

 // 테스트에 필요한 데이터 객체
 Student student1, student2;

 // 각각의 @Test 테스트 메소드가 실행되기 전에 자동 호출되는 메소드
 // 테스트에 필요한 데이터를 준비한다
// @Before
// @BeforeEach
// public void prepare() {
//  // 테스트에 필요한 데이터 객체를 생성한다.
//  student1 = new Student();
//  student1.setName("홍길동");
//  student1.setStudentNo("98769876");
//  student1.setEmail("h@skhu.net");
//  student1.setDepartmentId(1);
//  student1.setPhone("010-999-8888");
//  student1.setSex("남");
//
//  // 혹시 테스트용 데이터가 DB에 남아있다면 삭제한다.
//  Student s1 = studentRepository.findByStudentNo("98769876");
//  if (s1 != null) studentRepository.deleteById(s1.getId());
//
//  // 테스트에 필요한 데이터 객체를 생성한다.
//  student2 = new Student();
//  student2.setName("성춘향");
//  student2.setStudentNo("87878787");
//  student2.setEmail("s@skhu.net");
//  student2.setDepartmentId(2);
//  student2.setPhone("010-888-7777");
//  student2.setSex("여");
//
//  // 혹시 테스트용 데이터가 DB에 남아있다면 삭제한다.
//  Student s2 = studentRepository.findByStudentNo("87878787");
//  if (s2 != null) studentRepository.deleteById(s2.getId());
// }

 @Before
 public void prepare() {
  this.student = new Student();
  this.student.setId(337);
  this.student.setStudentNo("98989898");
  this.student.setName("임꺽정");
  this.student.setDepartmentId(2);
  this.student.setSex("남");;
  this.student.setPhone("010-999-8888");
  this.student.setEmail("lim@skhu.net");

  this.students = new ArrayList<Student>();
  this.students.add(this.student);
 }

 @Test
 void students() {
  // students URL을 GET 방식으로 서버에 요청하고 그 결과 데이터를 받는다.
  // 그 결과 테이터가 List<Studnet> 타입인지 확인한다.
  var s1 = webClient.get().uri("students")
          .exchange().expectStatus().isOk()
          .expectBodyList(Student.class).returnResult().getResponseBody();

  // 서버가 출력한 데이터가 맞는지, DB에서 조회한 데이터와 비교한다.
  List<Student> s2 = studentRepository.findAll();
  assertEquals(s1, s2);



 }
 // 테스트용 가짜 StudentService 객체
 @MockBean private StudentService studentService;
 Student student;
 List<Student> students;
 // 액션 메소드를 호출하기 위한 객체
 @Autowired private MockMvc mvc;

 @Test
 void student() {
  // 테스트 하기 위해서 mock 객체를 설정함.
  Mockito.when(studentService.findAll()) // studentService.findAll() 메소드가 호출되면,
          .thenReturn(this.students); // this.students 객체를 리턴하라

  // GET 방식으로 "/students" URL을 요청한다.
  // 액션 메소드가 리턴하는 json 데이터를 받는다
  ResultActions result = mvc.perform(
          get("/students").contentType(MediaType.APPLICATION_JSON));

  // 서버에서 받아온 json 데이터 확인
  result.andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(this.students.size())))  // 리턴된 목록 크기 확인
          .andExpect(jsonPath("$[0].id", is(this.student.getId()))) // 목록에서 첫번째 객체의 id값 확인
          .andExpect(jsonPath("$[0].studentNo", is(this.student.getStudentNo())))
          .andExpect(jsonPath("$[0].name", is(this.student.getName())))
          .andExpect(jsonPath("$[0].departmentId", is(this.student.getDepartmentId())))
          .andExpect(jsonPath("$[0].sex", is(this.student.getSex())))
          .andExpect(jsonPath("$[0].phone", is(this.student.getPhone())))
          .andExpect(jsonPath("$[0].email", is(this.student.getEmail())));

  // StudentSerivce 메소드가 호출되었음을 확인
  Mockito.verify(studentService).findAll();


 }

 @Test
 void insert() {
  // 테스트 하기 위해서 mock 객체를 설정함.
  Mockito.when(studentService.findById(ArgumentMatchers.anyInt()))
          .thenReturn(this.student);
  // studentService.findById(..) 메소드가 호출되면
  // this.student 객체를 리턴하라

  // GET 방식으로 "/student/337" URL을 요청한다.
  // 액션 메소드가 리턴하는 json 데이터를 받는다
  ResultActions result = mvc.perform(
          get("/student/" + this.student.getId())
                  .contentType(MediaType.APPLICATION_JSON));

  // 서버에서 받아온 json 데이터 확인
  result.andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(this.student.getId()))) // 리턴된 객체의 id값 확인
          .andExpect(jsonPath("$.name", is(this.student.getName())))
          .andExpect(jsonPath("$.studentNo", is(this.student.getStudentNo())))
          .andExpect(jsonPath("$.name", is(this.student.getName())))
          .andExpect(jsonPath("$.departmentId", is(this.student.getDepartmentId())))
          .andExpect(jsonPath("$.sex", is(this.student.getSex())))
          .andExpect(jsonPath("$.phone", is(this.student.getPhone())))
          .andExpect(jsonPath("$.email", is(this.student.getEmail())));

  // StudentSerivce 메소드가 호출되었음을 확인
  Mockito.verify(studentService).findById(this.student.getId());


 }

 @Test
 void update() {
 }

 @Test
 void delete() {
 }
}