package com.board.api;

import com.board.dto.CommentDto;
import com.board.entity.Comment;
import com.board.repository.CommentRepository;
import com.board.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CommentApiController {
  @Autowired
 CommentService commentService;

  @Autowired
 CommentRepository commentRepository;

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
 @PatchMapping("/api/comments/{id}")
 public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {

  // 서비스에게 위임
  CommentDto updatedDto = commentService.update(id, dto);

  // 결과 응답
  return ResponseEntity.status(HttpStatus.OK).body(updatedDto);

 }

 // 댓글 삭제
// 댓글 삭제
 @DeleteMapping("/api/comments/{id}")
 public ResponseEntity<CommentDto> delete(@PathVariable Long id) {

  // 서비스에게 위임
  CommentDto deletedDto = commentService.delete(id);

  // 결과 응답
  return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
 }



 // 닉네임조회
 @GetMapping("/api/articles/comments")
 public ResponseEntity nicknameComments(@RequestParam String nickname, Model model) {

  // 서비스단에 위임
  List<CommentDto> commentDtos=commentService.apiNickNameComments(nickname);

  return ResponseEntity.status(HttpStatus.OK).body(commentDtos);
 }

}















