package com.jpa1.controller;

import com.jpa1.entity.Book;
import com.jpa1.entity.Category;
import com.jpa1.entity.Department;
import com.jpa1.entity.Student;
import com.jpa1.repository.BookRepository;
import com.jpa1.repository.CategoryRepository;
import com.jpa1.repository.DepartmentRepository;
import com.jpa1.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("book")
public class BookController {
 @Autowired
 BookRepository bookRepository;
 @Autowired
 CategoryRepository categoryRepository;

 @RequestMapping("list")
 public String list(Model model) {
  List<Book> books = bookRepository.findAll();
  model.addAttribute("books", books);
  log.info(books.toString());
  return "book/list";
 }

 @GetMapping("create")
 public String create(Model model) {
  Book book = new Book();
  List<Category> categories = categoryRepository.findAll();
  model.addAttribute("book", book);
  model.addAttribute("categories", categories);
  return "book/edit";
 }

 @PostMapping("create")
 public String create(Model model, Book book) {
  bookRepository.save(book);
  return "redirect:list";
 }

 @GetMapping("edit")
 public String edit(Model model, @RequestParam("id") int id) {
  Book book = bookRepository.findById(id).orElse(null);
  List<Category> categories = categoryRepository.findAll();
  model.addAttribute("book", book);
  model.addAttribute("categories", categories);
  log.info(book.toString());
  return "book/edit";
 }

 @PostMapping(value="edit", params="cmd=save")
 public String edit(Model model, Book book) {
  bookRepository.save(book);
  return "redirect:list";
 }

 @PostMapping(value="edit", params="cmd=delete")
 public String delete(Model model, @RequestParam("id") int id) {
  bookRepository.deleteById(id);
  return "redirect:list";
 }

}
