package com.blog.api;

import com.blog.dto.ResponseDto;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestApiController {

 @Autowired private UserRepository userRepository;

 @PostMapping("/user/join2")
 public ResponseDto<Integer> join2(@RequestBody String user){
   ResponseDto dto=new ResponseDto(HttpStatus.OK.value(), 0);
   return  dto;
 }

 // 전체조회
 @GetMapping("/dummy/users")
 public List<User> listAll() {
  return userRepository.findAll();
 };

 // 페이징테스트
 // http://localhost:8088/dummy/user?page=1
 @GetMapping("/dummy/user")
 public Page<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {

  Page<User> users= userRepository.findAll(pageable);
  //Page<User> pagingUsers= userRepository.findAll(pageable);
  // List<User>   users= pagingUsers.getContent();

  return users;
 };

// // http://localhost:8088/dummy/user/1
// @GetMapping("/dummy/user/page/{page}")
// public Page<User> pageList2(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
//
//  Page<User> users= userRepository.findAll(pageable);
//  //Page<User> pagingUsers= userRepository.findAll(pageable);
//  // List<User>   users= pagingUsers.getContent();
//
//  return users;
// };


}//
