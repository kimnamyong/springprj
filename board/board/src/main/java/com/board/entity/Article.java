package com.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@SequenceGenerator(name = "a_seq", sequenceName = "article_seq", allocationSize = 1,initialValue = 1)
public class Article {

 @Id
 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "a_seq")
 private Long id;

 @Column
 private String title;

 @Column
 private String content;

 public void patch(Article article) {
  if(article.title != null)   this.title=article.title;
  if(article.content != null)   this.content=article.content;
 }

}
