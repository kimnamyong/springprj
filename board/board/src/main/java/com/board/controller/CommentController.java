package com.board.controller;

import com.board.entity.Comment;
import com.board.repository.CommentRepository;
import com.board.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class CommentController {

 @Autowired
 CommentRepository commentRepository;

 @Autowired
 CommentService commentService;

 // 닉네임조회
 @GetMapping("/articles/comments")
 public String nicknameComments(@RequestParam String nickname, Model model) {

  long start=System.currentTimeMillis();

  // 서비스단에 위임
  List<Comment> comments=commentService.nickNameComments(nickname);

  model.addAttribute("commentDtos", comments);
  model.addAttribute("nickname",nickname);
  // 3: 보여줄 페이지를 설정!

  long finish=System.currentTimeMillis();
  long timeMs=finish-start;

  log.info("닉네임조회 시간: " + timeMs +"ms");

  return "comments/_nickname";
 }

}
