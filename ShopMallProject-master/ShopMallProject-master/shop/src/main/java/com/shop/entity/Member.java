package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;
	private String picture;

	private String address; // 우편 번호

//  새롭게 추가된 코드
	private String streetaddr; // 지번 주소

//  새롭게 추가된 코드
	private String detailaddr; // 상세 주소

	@Enumerated(EnumType.STRING)
	private Role role;

	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		
		member.setAddress(memberFormDto.getZipcode());
		member.setDetailaddr(memberFormDto.getDetailaddr());
		member.setStreetaddr(memberFormDto.getStreetaddr());
		
		String password = passwordEncoder.encode(memberFormDto.getPassword());

		// 스프링 시큐리티 설정 클래스에 등록한 BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호 암호화
		member.setPassword(password);
		member.setRole(Role.USER);
		return member;
	}

	public static Member createAdminMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		
		member.setAddress(memberFormDto.getZipcode());
		member.setDetailaddr(memberFormDto.getDetailaddr());
		member.setStreetaddr(memberFormDto.getStreetaddr());
		
		String password = passwordEncoder.encode(memberFormDto.getPassword());

		// 스프링 시큐리티 설정 클래스에 등록한 BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호 암호화
		member.setPassword(password);
		member.setRole(Role.ADMIN); // Role 설정
		return member;
	}

	public void updatePassword(String encodePw) {		
		 this.setPassword(encodePw);
	}

}