package com.jpa1.controller;

import com.jpa1.entity.Book;
import com.jpa1.entity.Category;
import com.jpa1.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BookControllerTest {
@Autowired
 BookRepository bookRepository;

 @Test
 void list() {
   // 예상
  Book book = new Book();
  String book2="Book(id=2, title=죄와벌, author=도스토예프스키, price=10000, publisher=바다, category=Category(id=1, name=소설))";
  // 실제
  List<Book> books=bookRepository.findAll();
  // 검증
  log.info(books.toString());
  assertEquals(book2.toString(), books.toString());
 }

 @Test
 void create() {
  String title="노인과 바다";
  String author="이순신";
  //int category=3;
  int price=10000;
  String publisher ="영진출판사";
  // 예상
  Book book=new Book();
  Category category=new Category(3,"경제");
   book.setTitle(title);
   book.setAuthor(author);
   book.setCategory(category);
   book.setPublisher(publisher);
  // 실제
  Book book1=bookRepository.save(book);
  log.info(book1.toString());
  assertEquals(book.toString(), book1.toString());
  // 검증
 }

 @Test
 void testCreate() {
 }

 @Test
 void edit() {
 }

 @Test
 void testEdit() {
 }

 @Test
 void delete() {
 }
}