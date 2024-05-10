package com.board.controller;

import com.board.dto.ArticleForm;
import com.board.entity.Article;
import com.board.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

 @Autowired
 ArticleRepository articleRepository;

 @GetMapping("/articles")
 public String index(Model model){

  //  List<Article> articleEntityList= (List<Article>) articleRepository.findAll();
  List<Article> articleEntityList= articleRepository.findAll();

    model.addAttribute("articleList",articleEntityList);
    return "articles/index";
 }

 @GetMapping("/articles/new")
 public String newArticleForm(){
  return "articles/new";
 }

 @PostMapping("/articles/create")
 public String createArticle(ArticleForm articleForm){
  System.out.println(articleForm.toString());
  // 1. dto를 Entity 로 변환
  Article  article= articleForm.toEntity();

  // 2. repository에게 entity를 DB에 저장하게 한다.
  Article saved=articleRepository.save(article);
  System.out.println(saved.toString());
  // articleRepository.save(articleForm);

  return "";
 }


}
