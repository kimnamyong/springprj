package com.board.service;

import com.board.dto.ArticleForm;
import com.board.entity.Article;
import com.board.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

 @Autowired
 ArticleRepository articleRepository;

 public List<Article> index() {
  List<Article> articleList=articleRepository.findAll();
  return articleList;
 }

 public Article show(Long id) {
    return articleRepository.findById(id).orElse(null);
 }

 public Article create(ArticleForm dto) {
    Article article = dto.toEntity();
    Article saved=articleRepository.save(article);
    return saved;
 }
}
