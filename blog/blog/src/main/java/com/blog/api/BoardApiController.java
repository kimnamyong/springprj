package com.blog.api;

import com.blog.dto.ResponseDto;
import com.blog.model.Board;
import com.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

 @Autowired
 private BoardService boardService;

 @PostMapping("/api/board")
 public ResponseDto<Integer> save(@RequestBody Board board){
  boardService.글쓰기(board);
  return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
 }
}
