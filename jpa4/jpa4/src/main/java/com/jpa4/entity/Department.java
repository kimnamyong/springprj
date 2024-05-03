package com.jpa4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Entity
//@Table(name="department")
public class Department {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 int id;

 //@Column(name="departmentName")
 String name;

 String shortName;
 String phone;

 @JsonIgnore
 @ToString.Exclude
 @EqualsAndHashCode.Exclude
 @OneToMany(mappedBy="department")
 List<Student> students;

 @JsonIgnore
 @ToString.Exclude
 @EqualsAndHashCode.Exclude
 @OneToMany(mappedBy="department")
 List<Professor> professors;

}
