package com.spring4.mapper;


import com.spring4.dto.Book;
import com.spring4.dto.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
 @Select("""
       SELECT b.*, c.name bookCategory
       FROM book b JOIN category c ON b.categoryId = c.id """)
 List<Book> findAll();
}

