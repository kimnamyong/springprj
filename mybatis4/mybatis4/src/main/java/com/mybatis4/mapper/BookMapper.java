package com.mybatis4.mapper;


import com.mybatis4.dto.Book;
import com.mybatis4.dto.Student;
import com.mybatis4.model.Pagination;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
 @Select("""
         SELECT b.*, c.name bookCategory FROM book b JOIN category c ON b.categoryId = c.id
           ORDER BY b.id
            LIMIT #{firstRecordIndex}, #{sz} """)
 List<Book> findAll(Pagination pagination);

 @Select("SELECT COUNT(id) FROM Book")
 int getCount(Pagination pagination);

 @Insert("""
        INSERT Book (title, author, categoryId, price, publisher)
        VALUES (#{title}, #{author}, #{categoryId}, #{price}, #{publisher})
         """)
 @Options(useGeneratedKeys=false, keyProperty="id")
 void insert(Book book);

}