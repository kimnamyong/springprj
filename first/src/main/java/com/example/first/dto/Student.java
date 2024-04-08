package com.example.first.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Student {
  int id;
  String studentNumber;
  String studentName;
  String email;
}
