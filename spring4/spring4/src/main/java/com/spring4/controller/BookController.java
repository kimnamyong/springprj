package com.spring4.controller;

import com.spring4.mapper.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class BookController {

 @Autowired
 BookMapper bookMapper;  // DI

 @RequestMapping("book/list")
 public String list(Model model) {
  model.addAttribute("books", bookMapper.findAll());
  //log.info(bookMapper.findAll());
  return "book/list";
 }

}
