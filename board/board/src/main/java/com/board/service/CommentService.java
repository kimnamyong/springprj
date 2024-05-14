package com.board.service;

import com.board.dto.CommentDto;
import com.board.entity.Article;
import com.board.entity.Comment;
import com.board.repository.ArticleRepository;
import com.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

 @Transactional
 public CommentDto create(Long articleId, CommentDto dto) {

      Article article=  articleRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("댓글생성실패! 대상게시글이 없습니다."));

      // dto -> 엔티티로 생성
      Comment comment= Comment.createComment(dto,article);
      Comment created= commentRepository.save(comment);

      // 엔티티-> dto로 변경
      CommentDto createDto=CommentDto.createCommentDto(created);
      return createDto;

 }

 @Transactional
 public CommentDto update(Long id, CommentDto dto) {

  Comment target = commentRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

    target.patch(dto);  // 수정-> 더티체킹

// DB로 갱신
  // Comment updated = commentRepository.save(target);
// 엔티티에 변화가 있으면 알아서 save를 실행한다.

    return CommentDto.createCommentDto(target);
 }

 @Transactional
 public CommentDto delete(Long id) {
// 댓글 조회(및 예외 발생)
  Comment target = commentRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));

  // 댓글 삭제
  commentRepository.delete(target);

  // 삭제 댓글을 DTO로 반환
  return CommentDto.createCommentDto(target);

 }

 public List<Comment> nickNameComments(String nickname) {
  List<Comment> comments = commentRepository.findByNickname(nickname);
  return comments;
 }

// api
 public List<CommentDto> apiNickNameComments(String nickname) {

  List<Comment> comments=commentRepository.findByNickname(nickname);

  List<CommentDto> dtos= comments.stream()
          .map(comment -> CommentDto.createCommentDto(comment))
          .collect(Collectors.toList());
  return dtos;
 }

}//





















