package com.board.controller;

import com.board.dto.ArticleForm;
import com.board.entity.Article;
import com.board.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
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

  return "redirect:/articles";
  //return "redirect:/articles/"+saved.getId();
 }

 @GetMapping("/articles/{id}")
 public String show(@PathVariable Long id, Model model){
  // id로 데이터를 가져온다
  // Article articleEntity= articleRepository.findById(id).orElse(null);
  Article articleEntity= articleRepository.findById(id).orElseThrow(()-> {
           return new IllegalArgumentException("해당 유저는 없습니다. id:" + id);
          });


  // 가져온 데이터를 모델에 등록
  model.addAttribute("article", articleEntity);

  // 보여줄 페이지를 설정

  return "articles/show";
 }

 @GetMapping("/articles/{id}/edit")
 public String edit(@PathVariable Long id, Model model){

  Article articleEntity= articleRepository.findById(id).orElse(null);
 // Optional<Article> articleEntity= articleRepository.findById(id);

  model.addAttribute("article", articleEntity);

  // 수정 폼으로 데이터전송
  return "articles/edit";
 }

// 수정
 @PostMapping("/articles/update")
 public String update(ArticleForm articleForm){

  log.info("articleForm:"+articleForm.toString());
  // 1. dto를 Entity 로 변환
  Article  articleEntity= articleForm.toEntity();

  // DB에 기존 데이터를 가져온다.
   Article  target= articleRepository.findById(articleEntity.getId()).orElse(null);

  // 3. repository에게 entity를 DB에 저장하게 한다.
  if(target != null)
       articleRepository.save(articleEntity);

  //return "redirect:/articles";
  return "redirect:/articles/"+articleEntity.getId();
 }

 // 삭제
 @GetMapping("/articles/{id}/delete")
 public String delete(@PathVariable Long id, RedirectAttributes msg){

  Article target= articleRepository.findById(id).orElse(null);

  if(target != null) {
   articleRepository.delete(target);
   msg.addFlashAttribute("msg","삭제가 완료되었습니다.");// 휘발성데이터
     log.info("msg:" + msg.getFlashAttributes().toString());
  }

   return "redirect:/articles";
 }

}
