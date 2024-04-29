package com.mybatis4.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookEdit {
 int id;


 String title;

 @NotEmpty @NotBlank
 String author;

 int categoryId;

 int price;


 String publisher;

}

