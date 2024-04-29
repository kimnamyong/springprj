package com.resultMap1.dto;

import lombok.Data;

@Data
public class Sugang {
 int id;
 int lectureId;
 int studentId;
 boolean repeated;
 boolean cancel;
 String grade;

 Lecture lecture;
 Student student;

// String studentNo;
// String name;
//
// String title;
// int year;
// String semester;
}