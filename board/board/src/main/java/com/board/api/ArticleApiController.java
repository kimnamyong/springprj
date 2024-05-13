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
 public ResponseEntity  create(@RequestBody ArticleForm dto) {
  Article article = dto.toEntity();
  Article saved=articleRepository.save(article);
   return ResponseEntity.status(HttpStatus.OK).body(saved);
        // 200
 }

 @PatchMapping("/api/articles/{id}")
 public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
    Article article= dto.toEntity(); // 수정데이터보관

    Article target= articleRepository.findById(id).orElse(null);

    if(target == null || !id.equals(article.getId())){
     ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
     //ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

     target.patch(article);  // 엔티티 데이터 수정완료
     Article  updated  = articleRepository.save(target);
    // target을 article로 저장하면  post 요청이 된다(새글로 등록)

    return ResponseEntity.status(HttpStatus.OK).body(updated);
    // 상태코드와 응답본문을 클라이언트에게 전달
 }

 // DELETE
 @DeleteMapping("/api/articles/{id}")
 public ResponseEntity<Article> update(@PathVariable Long id){

  Article target= articleRepository.findById(id).orElse(null);
  if(target == null){
   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }
  articleRepository.delete(target);

  return ResponseEntity.status(HttpStatus.OK).build();
  // 200 OK 응답신호를 생성하고 본문을 갖지않는 경우에 build만 붙여준다.

 }



}  // end
