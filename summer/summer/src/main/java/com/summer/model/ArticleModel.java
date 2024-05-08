package com.summer.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ArticleModel {
 int id;

 @NotEmpty(message="제목을 입력하세요")
 String title;

 @Size(min=13, message="내용을 입력하세요")
 String body;
}
