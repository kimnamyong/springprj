package com.jpa1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Category {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 int id;

 String name;
}


