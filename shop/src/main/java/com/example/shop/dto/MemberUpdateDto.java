package com.example.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberUpdateDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=4, max=8, message="비밀번호는 4자 이상, 8자 이하로 입력해주세요")
    private String password;

    private String originalpassword;

    @NotEmpty(message = "우편번호는 필수 입력 값입니다.")
    private String zipcode;
    private String address;

    private String streetaddr;

    private String detailaddr;
}
