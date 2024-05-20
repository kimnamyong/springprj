package com.blog.controller;

import com.blog.model.Board;
import com.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BoardController {

 @Autowired
 private BoardService boardService;

  @GetMapping({"","/"})
  public String index(Model model) {

    List<Board> boards= boardService.글목록();
   model.addAttribute("boards",boards );

   return "index";
  }

 @GetMapping("/board/writeForm")
 public String saveForm(){
  return "board/saveForm";
 }
}
