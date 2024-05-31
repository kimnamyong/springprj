package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

 @GetMapping("/articles/new")
 public String newArticleForm() {
  return "articles/new";
 }
 @PostMapping("/articles/create")
 public String createArticle(ArticleForm form) {
  System.out.println(form.toString());
  return "";
 }
}