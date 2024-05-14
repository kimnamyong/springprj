package com.board.repository;

import com.board.entity.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

 @Override
 List<Article> findAll();
 List<Article> findAll(Sort sort);

}
