package com.bbs1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 int id;

 String loginName;
 String password;
 String name;
 String email;
 boolean enabled;
 String userType;

 @ManyToOne
 @JoinColumn(name="departmentId")
 Department department;

}
