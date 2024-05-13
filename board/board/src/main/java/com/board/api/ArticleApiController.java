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
 private final  ArticleService articleService;
// public ArticleApiController(ArticleService articleService) {
//  this.articleService = articleService;
// }

 // get
 @GetMapping("/api/articles")
 public List<Article> index(){
  List<Article> articleList = articleService.index();
  return  articleList;
  // localhost:8088/api/articles
 }

 // get 상세조회
 @GetMapping("/api/articles/{id}")
 public Article show(@PathVariable Long id){
  return  articleService.show(id);
  // http://localhost:8088/api/articles/1
 }

 // POST
 @PostMapping("/api/articles")
 public ResponseEntity  create(@RequestBody ArticleForm dto) {

    Article created=  articleService.create(dto);

    if(created != null){
     return ResponseEntity.status(HttpStatus.OK).body(created);
    }else{
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

 }

 @PatchMapping("/api/articles/{id}")
 public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
    Article article= dto.toEntity(); // 수정데이터보관
  log.info("id: {}, article: {}", id, article.toString());
    Article target= articleRepository.findById(id).orElse(null);
  log.info("id: {}, target: {}", id, target.toString());

    if(target == null || id != article.getId()){
     ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
     //ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

  log.info("id: {}, article2: {}", id, article);
     target.patch(article);  // 엔티티 데이터 수정완료
     Article  updated  = articleRepository.save(target);
    // target을 article로 저장하면  post 요청이 된다(새글로 등록)

    return ResponseEntity.status(HttpStatus.OK).body(updated);
    // 상태코드와 응답본문을 클라이언트에게 전달
 }

 // DELETE
 @DeleteMapping("/api/articles/{id}")
 public ResponseEntity<String> update(@PathVariable Long id){

  Article target= articleRepository.findById(id).orElse(null);
  if(target == null){
   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }
  articleRepository.delete(target);

  return ResponseEntity.status(HttpStatus.OK).build();
 // return ResponseEntity.status(HttpStatus.OK).body("삭제완료");
  // 200 OK 응답신호를 생성하고 본문을 갖지않는 경우에 build만 붙여준다.
 // return ResponseEntity.status(HttpStatus.OK).header("X-MyHeader","welcome").build();
 }



}  // end
