package com.board.api;

import com.board.dto.ArticleForm;
import com.board.entity.Article;
import com.board.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {

 @Autowired
 private ArticleRepository articleRepository;
// get
 @GetMapping("/api/articles")
 public List<Article> index(){
    //return  articleRepository.findAll();
  List<Article> articleList=articleRepository.findAll();
  return  articleList;
  // localhost:8088/api/articles
 }
 // get
 @GetMapping("/api/articles/{id}")
 public Article show(@PathVariable Long id){
  Article article =articleRepository.findById(id).orElse(null);
  return  article;
  // http://localhost:8088/api/articles/1
 }

 // POST
 @PostMapping("/api/articles")
 public Article create(@RequestBody ArticleForm dto) {
  Article article = dto.toEntity();
  return articleRepository.save(article);
 }

}
