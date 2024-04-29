package com.mybatis4.service;


import com.mybatis4.dto.Book;
import com.mybatis4.dto.Student;
import com.mybatis4.mapper.BookMapper;
import com.mybatis4.model.BookEdit;
import com.mybatis4.model.Pagination;
import com.mybatis4.model.StudentEdit;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Slf4j
@Service
public class BookService {
 @Autowired
 BookMapper bookMapper;  // DI

 ModelMapper modelMapper = new ModelMapper();

 public List<Book> findAll(Pagination pagination) {
  pagination.setRecordCount(bookMapper.getCount(pagination));
  return bookMapper.findAll(pagination);
 }

 public void insert(BookEdit bookEdit, BindingResult bindingResult,
                    Pagination pagination) throws Exception {

  log.info("bookEdit : {}",bookEdit);
  if (hasErrors(bookEdit, bindingResult))


    throw new Exception("입력 데이터 오류");
    Book book = toDto(bookEdit);
    bookMapper.insert(book);

    int lastPage = (int)Math.ceil((double)bookMapper.getCount(pagination) / pagination.getSz());
  pagination.setPg(lastPage);
 }


 public Book toDto(BookEdit bookEdit) {
  return modelMapper.map(bookEdit, Book.class);
 }

 public BookEdit toEditModel(Book booktDto) {
  return modelMapper.map(booktDto, BookEdit.class);
 }

 public boolean hasErrors(BookEdit bookEdit, BindingResult bindingResult) {
  if (bindingResult.hasErrors()) return true;

//  Book student2 = findByStudentNo(studentEdit.getStudentNo());
//
//  if (student2 != null && student2.getId() != studentEdit.getId()) {
//   bindingResult.rejectValue("studentNo", null, "학번이 중복됩니다.");
//   return true;
//  }

  return false;
 }
}
