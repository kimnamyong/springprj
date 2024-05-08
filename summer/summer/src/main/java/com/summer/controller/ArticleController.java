package com.summer.controller;

import com.summer.domain.Article;
import com.summer.domain.Board;
import com.summer.domain.User;
import com.summer.model.ArticleModel;
import com.summer.model.Pagination;
import com.summer.respository.ArticleRepository;
import com.summer.respository.BoardRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("article")
public class ArticleController {

 @Autowired
 ArticleRepository articleRepository;
 @Autowired
 BoardRepository boardRepository;

 @RequestMapping("list")
 public String list(Pagination pagination, Model model) {
  Board board = boardRepository.findById(pagination.getBd()).get();
  List<Article> list = articleRepository.findAll(pagination);
  log.info("list:{}",list);

  model.addAttribute("board", board);
  model.addAttribute("list", list);
  model.addAttribute("orderBy", ArticleRepository.orderBy);
  model.addAttribute("searchBy", ArticleRepository.searchBy);
  return "article/list";
 }

 @RequestMapping("view")
 public String view(@RequestParam("id") int id, Pagination pagination, Model model) {
  Article article = articleRepository.findById(id).get();
  model.addAttribute("article", article);
  return "article/view";
 }

 @RequestMapping(value="edit", method= RequestMethod.GET)
 public String edit(@RequestParam("id") int id, Pagination pagination, Model model) {
  Board board = boardRepository.findById(pagination.getBd()).get();
  Article a = articleRepository.findById(id).get();
  model.addAttribute("board", board);
  model.addAttribute("articleModel", a);
  return "article/edit";
 }

 @Transactional
 @RequestMapping(value="edit", method=RequestMethod.POST)
 public String edit(@Valid ArticleModel a, BindingResult bindingResult,
                    Pagination pagination, Model model) {
  if (bindingResult.hasErrors()) {
   Board board = boardRepository.findById(pagination.getBd()).get();
   model.addAttribute("board", board);
   return "article/edit";
  }
  articleRepository.update(a.getId(), a.getTitle(), a.getBody());
  return "redirect:view?id=" + a.getId() + "&" + pagination.getQueryString();
 }

 @RequestMapping(value="create", method=RequestMethod.GET)
 public String create(Pagination pagination, Model model) {
  Board board = boardRepository.findById(pagination.getBd()).get();
  model.addAttribute("board", board);
  model.addAttribute("articleModel", new ArticleModel());
  return "article/edit";
 }

 @Transactional
 @RequestMapping(value="create", method=RequestMethod.POST)
 public String create(@Valid ArticleModel a, BindingResult bindingResult,
                      Pagination pagination, Model model) {
  if (bindingResult.hasErrors()) {
   Board board = boardRepository.findById(pagination.getBd()).get();
   model.addAttribute("board", board);
   return "article/edit";
  }
  int id = insertArticle(a, pagination.getBd(), 1);
  return "redirect:view?id=" + id + "&" + pagination.getQueryString();
 }

 private int insertArticle(ArticleModel a, int boardId, int userId) {
  int maxNo = articleRepository.findMaxNo(boardId);

  Article article = new Article();

  article.setBoard(new Board());
  article.setUser(new User());
  article.setNo(maxNo + 1);
  article.setTitle(a.getTitle());
  article.setBody(a.getBody());
  //article.setWriteTime((Timestamp) new Date());

  articleRepository.save(article);

  return article.getId();
 }
}

