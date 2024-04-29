package com.mybatis4.controller;


import com.mybatis4.dto.Book;
import com.mybatis4.dto.Category;
import com.mybatis4.dto.Department;
import com.mybatis4.dto.Student;
import com.mybatis4.model.BookEdit;
import com.mybatis4.model.Pagination;
import com.mybatis4.model.StudentEdit;
import com.mybatis4.service.BookService;
import com.mybatis4.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
public class BookController {
 @Autowired
 CategoryService categoryService;

 @Autowired
 BookService bookService;

 ModelMapper modelMapper = new ModelMapper();

 @RequestMapping("book/list")
 public String list(Model model, Pagination pagination, HttpServletRequest request) {

  log.info("get URL {} ", request.getRequestURL().toString());

  pagination.setUrl(request.getRequestURL().toString());
  List<Book> books = bookService.findAll(pagination);
  model.addAttribute("books", books);
  return "book/list";
 }

 @GetMapping("book/create")
 public String create(Model model, Pagination pagination) {
  BookEdit bookEdit = new BookEdit();
  List<Category> categories =  categoryService.findAll();
  model.addAttribute("bookEdit", bookEdit);
  model.addAttribute("categories", categories);
  return "book/edit";
 }

 @PostMapping("book/create")
 public String create(Model model, Pagination pagination,
                      @Valid BookEdit bookEdit, BindingResult bindingResult) {
  try {
   bookService.insert(bookEdit, bindingResult, pagination);
   return "redirect:list?" + pagination.getQueryString();
  }
  catch (Exception e) {
   model.addAttribute("categories", categoryService.findAll());
   bindingResult.rejectValue("", null, "등록할 수 없습니다.");
   return "book/edit";
  }
 }

}
