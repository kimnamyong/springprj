package com.board.api;

import com.board.dto.CommentDto;
import com.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
  @Autowired
 CommentService commentService;

  // 댓글목록조회
  @GetMapping("/api/articles/{articleId}/comments")
  public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {

   // 서비스에게 댓글목록을 가져온다. 위임
   List<CommentDto> dtos = commentService.comments(articleId);

   // 결과 응답
   return ResponseEntity.status(HttpStatus.OK).body(dtos);
  }

 // 댓글생성
 @PostMapping("/api/articles/{articleId}/comments")
 public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {

// 서비스에게 위임
  CommentDto createdDto = commentService.create(articleId, dto);

  // 결과 응답
  return ResponseEntity.status(HttpStatus.OK).body(createdDto);
 }


 // 댓글수정

 // 댓글 삭제



}
