package com.mybatis2.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class Student {
 int id;
 String studentNo;
 String name;
 int departmentId;
 String email;
 String sex;
 String phone;
 String departmentName;

}
