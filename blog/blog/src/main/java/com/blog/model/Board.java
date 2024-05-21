package com.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data //Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
public class Board {
 @Id
 @GeneratedValue(strategy= GenerationType.IDENTITY)

 private int id;

 @Column(nullable=false,length=100)
 private String title;

 @Lob // 대용량 데이터
 @Column(columnDefinition = "longblob")
 private String content; //섬머노트라이브러리<html>태그

 @ColumnDefault("0")
 private int count; //조회수

 @ManyToOne // Many=Board, User=One,연관관계를 설정한다.
 @JoinColumn(name="userId") // DB에는 userId가 들어간다.
 private User user;

//@JoinColumn(name="replyId")
@OneToMany(mappedBy="board", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
@JsonIgnoreProperties({"board"})
@OrderBy("id desc")
@ToString.Exclude
 private List<Reply> replies;


 @CreationTimestamp
 private Timestamp createDate;
}