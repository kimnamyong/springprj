package com.summer.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data
@Entity
public class Article {
 @Id  // 대표값을 지정!  like a 주민등록번호
 @GeneratedValue   // 1, 2, 3, 자동생성 어노테이션
 private int id;

 private int no;

 @Column
 private String title;

 @ManyToOne
 @JoinColumn(name="boardId")
 private Board board;

 @ManyToOne
 @JoinColumn(name="userId")
 private User user;

  @Column
 private String body;

 @CreationTimestamp
 private Timestamp writeTime;
}
