package com.jpa1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
//@Getter
//@Setter
@Entity
public class Department {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 int id;

 String name;
 String shortName;
 String phone;

 @JsonIgnore
 @ToString.Exclude
 @EqualsAndHashCode.Exclude
 @OneToMany(mappedBy="department", fetch=FetchType.EAGER)
 List<Student> students;


}
