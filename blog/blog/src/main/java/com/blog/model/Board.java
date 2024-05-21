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
// reply가 외래키를 가지고 있으므로 연관관계의 주인이된다.
 // 연관관계의 주인이 아닌 객체는 mappedBy 속성을 사용해 주인필드의 변수명을 지정해주면 된다.
 // 외래키가 있는 곳을 주인으로 지정한다.
 // 연관관계의 주인은 연관관계를 갖는 두 객체 사이에서 조회, 저장 , 수정, 삭제를 할 수 있지만  주인이 아니면 조회만 가능하다.

 @CreationTimestamp
 private Timestamp createDate;
}