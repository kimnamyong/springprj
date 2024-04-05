package com.example.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.Member;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Form4Controller {

    @GetMapping("form4/login1")
    public String login1(Model model) {
        model.addAttribute("now", new Date());
        log.info("welcome");
        return "form4/login1";
    }

    @PostMapping("form4/login1")
    public String login1(Model model, String userid, String password, Boolean autologin, HttpSession session) {
    	String errorMsg = null;
     if (userid == null || userid.length() == 0)
         errorMsg = "사용자 아이디를 입력하세요";
     else if (password == null || password.length() == 0)
         errorMsg = "비밀번호를 입력하세요";
     else if (userid.equals(password) == false)
         errorMsg = "비밀번호 불일치";
     else {
         session.setAttribute("userid", userid);
         session.setAttribute("autologin", autologin);
         return "redirect:login_success";
     }
     model.addAttribute("now", new Date());
     model.addAttribute("userid", userid);
     model.addAttribute("password", password);
     model.addAttribute("autologin", autologin);
     model.addAttribute("errorMsg", errorMsg);
     
     //log.info(model);
     
     return "form4/login1";
    }
    
    @GetMapping("form4/login_success")
    public String login_success(Model model) {
        return "form4/login_success";
    }

    @PostMapping("form5/register1")
    public String register1(Model model, HttpSession session, Member member)
    {
        String errorMsg = null;
        if (member.getUserid() == null || member.getUserid().length() == 0)
            errorMsg = "사용자 아이디를 입력하세요";
        else if (member.getName() == null || member.getName().length() == 0)
            errorMsg = "이름을 입력하세요";
        else if (member.getPassword1() == null || member.getPassword1().length() == 0)
            errorMsg = "비밀번호1을 입력하세요";
        else if (member.getPassword2() == null || member.getPassword2().length() == 0)
            errorMsg = "비밀번호2를 입력하세요";
        else if (member.getPassword1().equals(member.getPassword2()) == false)
            errorMsg = "비밀번호 불일치";
        else if (member.getEmail() == null || member.getEmail().length() == 0)
            errorMsg = "이메일 주소를 입력하세요";
        else {
            session.setAttribute("member", member);
//            session.setAttribute("name", name);
//            session.setAttribute("email", email);
            return "redirect:register_success1";
        }
//        model.addAttribute("userid", userid);
//        model.addAttribute("name", name);
//        model.addAttribute("email", email);
//        model.addAttribute("departmentId", departmentId);
      model.addAttribute("errorMsg", errorMsg);
          model.addAttribute("member",member);
        return "form5/register1";
    }

    @GetMapping("form5/register1")
    public String register1(Model model) {
        return "form5/register1";
    }

    @RequestMapping("form5/register_success1")
    public String register_success1(Model model) {
        return "form5/register_success1";
    }

    
    
} // end
