package com.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
 private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
 // 자바와 데이터베이스가 충돌난다.

//@JoinColumn(name="replyId")
 @OneToMany(mappedBy="board", fetch=FetchType.EAGER)
 // mappedBy 연관관계의 주인이 아니다(난 FK가아니다)
 // DB에 컬럼을 만들지않는다. 그냥 Board를 select 할때 join문을 통해 값을 얻기위한 것이다.
 // board는 reply에 있는 board를 적어준다. 기본값은 LAZY이다.
//  board select user와 reply 정보를 다 들고와야 한다.
 private List<Reply> reply;


 @CreationTimestamp
 private Timestamp createDate;
}