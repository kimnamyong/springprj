package com.blog.service;

import com.blog.model.Board;
import com.blog.model.User;
import com.blog.repository.BoardRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
 @Autowired
 private BoardRepository boardRepository;

 @Autowired
 private HttpSession session;

 @Transactional
 public void 글쓰기(Board board){ // title, content
  User user= (User) session.getAttribute("principal");

  board.setCount(0); // 조회수 0
  board.setUser(user);

  boardRepository.save(board);
 }

 public Page<Board> 글목록(Pageable pageable) {
  return boardRepository.findAll(pageable);
 }

 @Transactional
 public Board 글상세보기(int id) {
  Board board =boardRepository.findById(id).orElseThrow(()->{
   return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
  });
  // 조회수 증가
  board.setCount(board.getCount()+1);
  //boardRepository.save(board);
  return board;
 }
}
