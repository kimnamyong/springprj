package com.resultMap2.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Department {
 int id;
 String name;
 String shortName;
 String phone;

 List<Student> students;
}

