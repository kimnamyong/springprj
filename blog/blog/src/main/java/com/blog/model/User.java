package com.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert  insert시 null 인 필드를 제외시켜준다.
public class User {
 @Id
 @GeneratedValue(strategy= GenerationType.IDENTITY)
 private int id;

 @Column(nullable=false,length=30)
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
}