package com.board.service;

import com.board.dto.ArticleForm;
import com.board.entity.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

 @Autowired
 ArticleService articleService;

 @Test
 void index() {
// 예상
  Article a = new Article(1L, "가가가가", "1111");
  Article b = new Article(2L, "나나나나", "2222");
  Article c = new Article(3L, "다다다다", "3333");
  List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));

  // 실제
  List<Article> articles = articleService.index();

  // 검증
  assertEquals(expected.toString(), articles.toString());

 }

 @Test
 void show_성공() {
// 예상
  Long id = 22L;
  Article expected = new Article(id, "김치", "만두2");
  // 실제
  Article article = articleService.show(id);
  // 비교
  assertEquals(expected.toString(), article.toString());
 }

 @Test
 void show_실패() {
// 예상
  Long id = -1L;
  Article expected = new Article(id, "김치", "만두2");
  ;
  // 실제
  Article article = articleService.show(id);
  // 비교
  assertEquals(expected, article);
 }

 @Test
 void create_성공() {
// 예상
  String title = "오늘도";
  String content = "수고했습니다.";
  ArticleForm dto = new ArticleForm(null, title, content);
  Article expected = new Article(22L, title, content);

  // 실제
  Article article = articleService.create(dto);

  assertEquals(expected.toString(), article.toString());
 }

 @Test
 void create_실패() {
// 예상
  String title = "오늘도222";
  String content = "수고했습니다.2";
  ArticleForm dto = new ArticleForm(24L, title, content);
  Article expected = null;

  // 실제
  Article article = articleService.create(dto);

  Assertions.assertEquals(expected, article);
 }

 @Test
 void update_성공() {
  // 예상
  Long id = 6L;
  String title = "welcome to my world";
  String content = "welcome to my world. happy new year";

  ArticleForm dto = new ArticleForm(id, title, content);
  Article expected = new Article(6L, title, content);

  // 실제
  Article article = articleService.update(id, dto);
  Assertions.assertEquals(expected.toString(), article.toString());
 }

 @Test
 void update_실패() {
  // 예상
  Long id = 116L;
  String title = "welcome to my world";
  String content = "welcome to my world. happy new year";

  ArticleForm dto = new ArticleForm(id, title, content);
  Article expected = null;

  // 실제
  Article article = articleService.update(id, dto);
  Assertions.assertEquals(expected, article);
 }

 @Test
 void delete_성공(){
  // 예상
  Long id = 22L;
  ArticleForm dto = new ArticleForm(id,null,null);
  Article expected = new Article(id,"김치", "만두");

  // 실제
  Article article = articleService.delete(id);

  // 비교
  assertEquals(expected.toString(), article.toString());
 }

 @Test
 void delete_실패(){
// 예상
  Long id = 66L;        // 존재하지 않는 id 입력할 경우
  ArticleForm dto = new ArticleForm(id,null,null);
  Article expected = null;

  // 실제
  Article article = articleService.delete(id);

  // 비교
  assertEquals(expected, article);
 }


} // 끝