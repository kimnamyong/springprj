package com.test3.controller;

import com.test3.entity.Student;
import com.test3.service.StudentService;
import com.test3.util.JsonUtils;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.Matchers.is;

@AutoConfigureMockMvc
@SpringBootTest
public class StudentControlerTests1 {

 // 액션 메소드를 호출하기 위한 객체
 @Autowired private MockMvc mvc;

 // 테스트용 가짜 StudentService 객체
 @MockBean
 private StudentService studentService;

 // 테스트에 사용할 데이터
 Student student;
 List<Student> students;

 @Before("")
 public void prepare() {
  this.student = new Student();
  this.student.setId(337);
  this.student.setStudentNo("98989898");
  this.student.setName("임꺽정");
  this.student.setDepartmentId(2);
  this.student.setSex("남");;
  this.student.setPhone("010-999-8888");
  this.student.setEmail("lim@skhu.net");

  this.students = new ArrayList<>();
  this.students.add(this.student);
 }

 @Test
 public void test_students() throws Exception {
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
 public void test_student() throws Exception {
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
 public void test_student_insert_success() throws Exception {
  // 테스트 하기 위해서 mock 객체를 설정함.
  Mockito.doNothing().when(studentService).insert(ArgumentMatchers.any());
  // studentService.insert(...) 메소드가 호출되면,
  // 아무것도 하지 않도록 설정함

  // POST 방식으로 "/student" URL을 요청한다.
  // 이때 this.student 객체를 JSON 형식으로 서버에 전송함.
  // 액션 메소드가 리턴하는 json 데이터를 받는다
  ResultActions result = mvc.perform(
          post("/student")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(JsonUtils.toJson(this.student)));

  // 서버에서 받아온 json 데이터 확인
  result.andExpect(status().isOk())
          .andExpect(jsonPath("$.success", is(true)));

  // StudentSerivce.insert 메소드가 호출되었고,
  // 파라미터 값이 this.student 객체의 값과 같은지 확인 (equals)
  Mockito.verify(studentService).insert(this.student);
 }

 @Test
 public void test_student_insert_error() throws Exception {
  // 테스트 하기 위해서 mock 객체를 설정함.
  String errorMessage = "학번중복";
  Mockito.doThrow(new Exception(errorMessage))
          .when(studentService).insert(ArgumentMatchers.any());
  // studentService.insert(...) 메소드가 호출되면,
  // Exception을 throw하도록 설정함.


  // POST 방식으로 "/student/" URL을 요청함.
  // 이때 this.student 객체를 JSON 형식으로 서버에 전송함.
  // 액션 메소드가 리턴하는 json 데이터를 받는다
  ResultActions result = mvc.perform(
          post("/student")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(JsonUtils.toJson(this.student)));

  // 서버에서 받아온 json 데이터 확인
  result.andExpect(status().isOk())
          .andExpect(jsonPath("$.success", is(false)))
          .andExpect(jsonPath("$.message", is(errorMessage)));
// JSON 응답에서 success 필드의 값이 false인지 확인합니다.
  // StudentSerivce.insert 메소드가 호출되었고,
  // 파라미터 값이 this.student 객체의 값과 같은지 확인 (equals)
  Mockito.verify(studentService).insert(this.student);
 }

 @Test
 public void test_student_update() throws Exception {
  // 테스트 하기 위해서 mock 객체를 설정함.
  Mockito.doNothing().when(studentService).update(ArgumentMatchers.any());
  // studentService.update(...) 메소드가 호출되면,
  // 아무것도 하지 않도록 설정함

  // PUT 방식으로 "/student" URL을 요청한다.
  // 이때 this.student 객체를 JSON 형식으로 서버에 전송함.
  // 액션 메소드가 리턴하는 json 데이터를 받는다
  ResultActions result = mvc.perform(
          put("/student")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(JsonUtils.toJson(this.student)));

  // 서버에서 받아온 json 데이터 확인
  result.andExpect(status().isOk())
          .andExpect(jsonPath("$.success", is(true)));

  // StudentSerivce.update 메소드가 호출되었고,
  // 파라미터 값이 this.student 객체의 값과 같은지 확인 (equals)
  Mockito.verify(studentService).update(this.student);
 }

 @Test
 public void test_student_delete() throws Exception {
  // 테스트 하기 위해서 mock 객체를 설정함.
  Mockito.doNothing().when(studentService).deleteById(ArgumentMatchers.anyInt());
  // studentService.delete(...) 메소드가 호출되면,
  // 아무것도 하지 않도록 설정함

  // DELETE 방식으로 "/student/337" URL을 요청한다.
  ResultActions result = mvc.perform(
          delete("/student/337"));

  // 서버에서 받아온 json 데이터 확인
  result.andExpect(status().isOk())
          .andExpect(jsonPath("$.success", is(true)));

  // StudentSerivce.deleteById 메소드가 호출되었고,
  // 파라미터 값이 337 인지 확인
  Mockito.verify(studentService).deleteById(337);
 }

}
