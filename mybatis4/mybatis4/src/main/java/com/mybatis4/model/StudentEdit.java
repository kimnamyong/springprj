package com.mybatis4.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentEdit {
 int id;

 @NotEmpty
 @NotBlank
 @Size(min=8, max=12)
 String studentNo;

 @NotEmpty @NotBlank
 @Size(min=2, max=20)
 String name;

 @NotEmpty @NotBlank
 @Pattern(regexp="010-[0-9]{3,4}-[0-9]{4}",
         message="휴대폰 번호를 입력하세요 예:010-000-0000")
 String phone;

 @NotEmpty @Email
 @Pattern(regexp="^[a-zA-Z0-9]+@[a-z]+(\\.[a-z]{2,3})*",
         message="이메일 주소가 올바르지 않습니다. 예:admin@daum.net")
 String email;

 @NotEmpty @NotBlank
 @Pattern(regexp="남|여", message="남, 여 중 하나를 입력하세요.")
 String sex;

 @Min(value=1, message="학과를 선택하세요.")
 int departmentId;
}

