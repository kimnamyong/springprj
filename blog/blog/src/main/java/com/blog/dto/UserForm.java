package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserForm {
 private String username;
 private String password;
 private String email;
 private String role;
}
