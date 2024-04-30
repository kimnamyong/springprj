package com.jpa1.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 int id;

 String title;
 String author;
 //int categoryId;
 int price;
 String publisher;

 @ManyToOne
 @JoinColumn(name="categoryId")
 Category category;
}
