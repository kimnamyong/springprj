package com.blog.api;

import com.blog.config.PrincipalDetail;
import com.blog.config.PrincipalDetailService;
import com.blog.dto.ReplySaveRequestDto;
import com.blog.dto.ResponseDto;
import com.blog.model.Board;
import com.blog.model.Reply;
import com.blog.model.User;
import com.blog.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class BoardApiController {

 @Autowired
 private BoardService boardService;

 @Autowired
 HttpSession session;

 @Autowired
 private PrincipalDetailService principalDetailService;


 // @PostMapping("/api/board")
// public ResponseDto<Integer> save(@RequestBody Board board){
//  Boolean isSession= boardService.글쓰기(board);
//  if(isSession==true){
//   return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//  }else{
//   return new ResponseDto<Integer>(HttpStatus.OK.value(),0);
//  }
// }
 @PostMapping("/api/board")
 public ResponseDto<Integer> save(@RequestBody Board board){


  SecurityContext securityContext = SecurityContextHolder.getContext();
  Authentication authentication = securityContext.getAuthentication();
  UserDetails userDetails = (UserDetails) authentication.getPrincipal();

  log.info("securityContext:"+securityContext);
  log.info("authentication:"+authentication);
  log.info("userDetails:"+userDetails);

   boardService.글쓰기(board,userDetails.getUsername());

   return new ResponseDto<Integer>(HttpStatus.OK.value(),1);

 }

 @DeleteMapping("/api/board/{id}")
 public ResponseDto<Integer> deleteById(@PathVariable int id){
  boardService.글삭제하기(id);
  return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
 }

 @PutMapping("/api/board/{id}")
 public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board ){
  boardService.글수정하기(id,board);
  return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
 }

 // 댓글올리기-1

// @PostMapping("/api/board/{boardId}/reply")
// public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply){
//  User user= (User) session.getAttribute("principal");
//
//  boardService.댓글쓰기(user, boardId, reply);
//  return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
// } //

 @PostMapping("/api/board/{boardId}/reply")
 public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){

  boardService.댓글쓰기(replySaveRequestDto);

  return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
 }



 // 댓글수정하기
 @PutMapping("/api/board/{boardId}/reply/{replyId}")
 public ResponseDto<Integer> replyUpdate(@PathVariable int replyId, @RequestBody Reply reply){
  //User user= (User) session.getAttribute("principal");
  boardService.댓글수정(replyId, reply);
  return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
 }


 @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
 public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
  boardService.댓글삭제(replyId);
  return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
 }



} //
