package com.example.shop.entity;

import com.example.shop.constant.Role;
import com.example.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity implements UserDetails {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String originalpassword;
    private String picture;

    private String address; // 우편 번호
    private String zipcode; // 우편 번호

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
        member.setZipcode(memberFormDto.getZipcode());
        member.setAddress(memberFormDto.getAddress());
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

//    public void updatePassword(String encodePw) {
//        this.setPassword(encodePw);
//    }

    /**
     * 회원수정 메소드
     */
    public void updateUsername(String name) {
        this.name = name;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateOriginalPassword(String originalpassword) {
        this.originalpassword = originalpassword;
    }

    public void updateAddress(String address) {
        this.address = address;
    }
    public void updateZipCode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void updateStreetAddress(String streetaddress) {
        this.streetaddr = streetaddress;
    }

    public void updateDetailAddress(String detailaddress) {
        this.detailaddr = detailaddress;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
