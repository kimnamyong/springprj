package com.board.api;

import com.board.dto.ArticleForm;
import com.board.entity.Article;
import com.board.repository.ArticleRepository;
import com.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ArticleApiController {

 @Autowired
 private ArticleRepository articleRepository;

 // 필드주입
// @Autowired
// private ArticleService articleService;

 // 생성자주입(DI)
 private final ArticleService articleService;
// public ArticleApiController(ArticleService articleService) {
//  this.articleService = articleService;
// }

 // get
 @GetMapping("/api/articles")
 public List<Article> index() {
  List<Article> articleList = articleService.index();
  return articleList;
  // localhost:8088/api/articles
 }

 // get 상세조회
 @GetMapping("/api/articles/{id}")
 public Article show(@PathVariable Long id) {
  return articleService.show(id);
  // http://localhost:8088/api/articles/1
 }

 // POST
 @PostMapping("/api/articles")
 public ResponseEntity create(@RequestBody ArticleForm dto) {


   Article created = articleService.create(dto);

  if (created != null) {
   return ResponseEntity.status(HttpStatus.OK).body(created);
  } else {
   return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

 }

 @PatchMapping("/api/articles/{id}")
 public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
  log.info("id:{}", id);
  Article updated = articleService.update(id, dto);

  if (updated != null) {
   return ResponseEntity.status(HttpStatus.OK).body(updated);
  } else {
   return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

 }

 // DELETE
 @DeleteMapping("/api/articles/{id}")
 public ResponseEntity<Article> update(@PathVariable Long id) {

  Article deleted = articleService.delete(id);
  log.info("deleted:{}", deleted);
  if (deleted != null) {
   return ResponseEntity.status(HttpStatus.OK).body(deleted);
   //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  } else {
   return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }
 }


 // 트랜잭션 -> 실패 -> 롤백!
 @PostMapping("/api/transaction-test")
 public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {

  List<Article> createdList = articleService.createArticles(dtos);
  return (createdList != null) ?
          ResponseEntity.status(HttpStatus.OK).body(createdList) :
          ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

 }


}  // end
