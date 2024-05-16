package com.board.dto;

import com.board.entity.UserEntity;
import lombok.*;
import org.apache.catalina.User;

import java.sql.Timestamp;

@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class UserForm {
 private Long id;
 private String username;
 private String password;
 private String email;
 private String role;
 private Timestamp createDate;

 public UserEntity toEntity() {
  return new UserEntity(id, username, password, email, role, createDate);
 }
}
