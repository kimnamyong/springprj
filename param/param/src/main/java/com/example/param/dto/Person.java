package com.example.param.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
 String name;
 double weight;
 Date birthday;

 @DateTimeFormat(pattern="yyyy-MM-dd")
 public void setBirthday(Date birthday) {
  this.birthday = birthday;
 }

}

