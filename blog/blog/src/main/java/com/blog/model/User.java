package com.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert // insert시 null 인 필드를 제외시켜준다.
public class User {
 @Id
 @GeneratedValue(strategy= GenerationType.IDENTITY)
 private int id;

 @Column(nullable=false,length=30, unique = true)
 private String username;

 @Column(nullable=false,length=100)
 private String password;

 @Column(nullable=false,length=50)
 private String email;

// @ColumnDefault("'user'")
// private String role;
 // DB는 RoleType이라는게 없다.
 @Enumerated(EnumType.STRING)
 private RoleType role;

 @CreationTimestamp  // 시간이 자동 입력
 private Timestamp createDate;

 @OneToMany(mappedBy="user", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
 @JsonIgnoreProperties({"user"})
 @ToString.Exclude
 @OrderBy("id desc")
 private List<Board> boards;

}