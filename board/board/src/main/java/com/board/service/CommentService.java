package com.board.service;

import com.board.dto.CommentDto;
import com.board.entity.Article;
import com.board.entity.Comment;
import com.board.repository.ArticleRepository;
import com.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

 @Autowired
 CommentRepository commentRepository;

 @Autowired
 ArticleRepository articleRepository;

 public List<CommentDto> comments(Long articleId) {
  // 댓글목록 조회
   List<Comment> comments= commentRepository.findByArticleId(articleId);

   // 엔티티 -> DTO로 변환
//     List<CommentDto>  dtos=new ArrayList<CommentDto>();
//      for(int i=0;i<comments.size();i++){
//         Comment c= comments.get(i);
//        CommentDto dto= CommentDto.createCommentDto(c);
//        dtos.add(dto);
//      }
  List<CommentDto> dtos= comments.stream()
          .map(comment -> CommentDto.createCommentDto(comment))
          .collect(Collectors.toList());

  return dtos;
 }

 public CommentDto create(Long articleId, CommentDto dto) {

      Article article=  articleRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("댓글생성실패! 대상게시글이 없습니다."));

      // dto -> 엔티티로 생성
      Comment comment= Comment.createComment(dto,article);
      Comment created= commentRepository.save(comment);

      // 엔티티-> dto로 변경
      CommentDto createDto=CommentDto.createCommentDto(created);
      return createDto;
 }

}
