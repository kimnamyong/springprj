package com.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data //Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
public class Reply {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;

 @Column(nullable = false, length = 200)
 private String content;

 @ManyToOne  // Many->Board, One-> Reply
 @JoinColumn(name = "boardId")
 private Board board;
 // 하나의 게시글에는 여러개의 답변이 있다.

 @ManyToOne  // Many->User, One->Reply
 @JoinColumn(name = "userId")
 private User user;
 // 한 사용자가 여러개의 답변을 만들 수 있다.

 @CreationTimestamp
 private Timestamp createDate;

 public void update(User user, Board board, String content) {
  setUser(user);
  setBoard(board);
  setContent(content);

 }
}