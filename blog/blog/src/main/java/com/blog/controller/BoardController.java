package com.blog.controller;

import com.blog.model.Board;
import com.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class BoardController {

 @Autowired
 private BoardService boardService;

  @GetMapping({"","/"})
  public String index(Model model,@PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {

    Page<Board> boards= boardService.글목록(pageable);
   model.addAttribute("boards",boards );

   return "index";
  }

 @GetMapping("/board/writeForm")
 public String saveForm(){
  return "board/saveForm";
 }

 // 상세페이지
 @GetMapping("/board/{id}")
 public String findById(@PathVariable int id, Model model){
  model.addAttribute("board", boardService.글상세보기(id));
  return "board/detail";
 }

} //end
