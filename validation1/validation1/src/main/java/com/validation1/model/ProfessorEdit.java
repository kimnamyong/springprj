package com.validation1.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProfessorEdit {

 int id;

 @NotEmpty @NotBlank
 @Size(min=2, max=20)
 String name;

 int departmentId;

 @NotEmpty @NotBlank
 @Pattern(regexp="010-[0-9]{3,4}-[0-9]{4}")
 String phone;

 @NotEmpty @Email
 String email;

 @NotEmpty @NotBlank
 String office;

 @NotEmpty @NotBlank
 String departmentName;

}
