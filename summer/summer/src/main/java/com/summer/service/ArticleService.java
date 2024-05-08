package com.summer.service;

import com.summer.domain.Article;
import com.summer.domain.Board;
import com.summer.domain.User;
import com.summer.model.ArticleModel;
import com.summer.model.Option;
import com.summer.model.Pagination;
import com.summer.respository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

 @Autowired
 ArticleRepository articleRepository;

 public Article findOne(int id) {
  return articleRepository.findById(id).get();
 }

 public List<Article> findAll(Pagination pagination) {
  return articleRepository.findAll(pagination);
 }

 public void update(ArticleModel a) {
  articleRepository.update(a.getId(), a.getTitle(), a.getBody());
 }

 public int insertArticle(ArticleModel a, int boardId, int userId) {
  int maxNo = articleRepository.findMaxNo(boardId);

  Article article = new Article();
  article.setBoard(new Board());
  //article.getBoard().setId(boardId);
  article.setUser(new User());
  article.getUser().setId(userId);
  //article.setWriteTime(new Date());
  article.setNo(maxNo + 1);
  article.setTitle(a.getTitle());
  article.setBody(a.getBody());
  articleRepository.save(article);
  return  article.getId();
 }

 public void delete(int id) {
  articleRepository.deleteById(id);
 }

 public Option[] getOrderByOptions() {
  return ArticleRepository.orderBy;
 }

 public Option[] getSearchByOptions() {
  return ArticleRepository.searchBy;
 }
}
