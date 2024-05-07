package com.security1.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ProfessorController {

 @Secured("ROLE_PROFESSOR")
 @GetMapping("professor/index")
 public String index(Model model) {
  return "professor/index";
 }

 @ExceptionHandler(AccessDeniedException.class)
 public String accessDenied(HttpServletRequest request, Exception exception) {
  log.info("Exception1:"+exception);
  return "error/accessDenied";
 }
}

