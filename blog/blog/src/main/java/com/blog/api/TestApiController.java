package com.blog.api;

import com.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {


 @PostMapping("/user/join2")
 public ResponseDto<Integer> join2(@RequestBody String user){

   ResponseDto dto=new ResponseDto(HttpStatus.OK.value(), 0);

   return  dto;
 }
}
