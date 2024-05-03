package com.shop.controller;

import com.shop.dto.MailDto;
import com.shop.dto.MemberFormDto;
import com.shop.service.MailService;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.validation.BindingResult;

import java.security.Principal;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
 @Autowired
 private MemberRepository memberRepository;
 
 @Autowired
 private MailService mailService;
 

	@GetMapping(value = "/new")
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}

	@PostMapping(value = "/new")
	public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "member/memberForm";
		}

		try {
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberForm";
		}

		return "redirect:/";
	}

	@GetMapping(value = "/newAdmin")
	public String adminMemberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}

	// 검증하려는 객체의 앞에 @Valid 어노테이션을 선언하고, 파라미터로 bindingResult 객체 추가

	// 검사 후 결과는 bindingResult에 담아줌.
	@PostMapping(value = "/newAdmin")
	public String newAdminMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // 에러가 있다면 회원 가입 페이지로 이동
			return "member/memberForm";
		}

		try {
			Member member = Member.createAdminMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());

			// 회원 가입 시 중복 회원 가입 예외가 발생하면 에러 메시지를 뷰로 전달
			return "member/memberForm";
		}
		return "redirect:/";
	}

	@GetMapping(value = "/login")
	public String loginMember() {
		return "/member/memberLoginForm";
	}

	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
		return "/member/memberLoginForm";
	}
	
	@GetMapping("/loginInfo")
 public String memberInfo(Principal principal, ModelMap modelMap){
     String loginId = principal.getName();
     Member member = memberRepository.findByEmail(loginId);
     modelMap.addAttribute("member", member);

     return "mypage/myinfo";
 }
	
 // 회원 비밀번호 찾기
 @GetMapping(value = "/findMember")
 public String findMember(Model model) {
     return "/member/findMemberForm";
 }

 // 비밀번호 찾기시, 임시 비밀번호 담긴 이메일 보내기
 @Transactional
 @PostMapping("/sendEmail")
 public String sendEmail(@RequestParam("memberEmail") String memberEmail){
 	
     MailDto dto = mailService.createMailAndChangePassword(memberEmail);
     mailService.mailSend(dto);

     return "/member/memberLoginForm";
 }
	

 @RequestMapping(value = "/findId", method = RequestMethod.POST)
 @ResponseBody
 public String findId(@RequestParam("memberEmail") String memberEmail) {
     String email = String.valueOf(memberRepository.findByEmail(memberEmail));
     System.out.println("회원 이메일 = " + email);
     if(email == null) {
         return null;
     } else {
         return email;
     }
 }
	
	

}