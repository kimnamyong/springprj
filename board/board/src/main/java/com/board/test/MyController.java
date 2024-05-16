package com.board.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
public class MyController {
 @GetMapping("/hellotest")
 public String hello(@RequestParam(name = "name", required = false, defaultValue = "Guest") String name,
                     Model model) {
  String message = "Hello, " + name + "!";
  model.addAttribute("message", message);
  return "hello"; // hello.html 뷰를 반환
 // localhost:8088/hellotest?name=admin
 }

 @GetMapping("/hellotest2")
 public String hello2(Model model) {
  String name = "Welcome";
  String message = "Hello, ";
  // String[] fruits={"사과","배","바나나","망고"};
  ArrayList<String> fruits=new ArrayList<>();
  fruits.add("사과");
  fruits.add("배");
  fruits.add("바나나");

  model.addAttribute("message", message);
  model.addAttribute("name", name);
  model.addAttribute("fruits", fruits);

  log.info("과일 : " +fruits);
  return "hello"; // hello.mustache 뷰를 반환
  // localhost:8088/hellotest2
 }

 @GetMapping("/redirect")
 public String redirectToAnotherPage(RedirectAttributes redirectAttributes) {

  // 플래시 속성 추가
  redirectAttributes.addFlashAttribute("message", "This is a flash message!");

  // 리다이렉트할 URL 리턴
  return "redirect:/anotherPage";
 }

 @GetMapping("/anotherPage")
 public String showAnotherPage(@ModelAttribute("message") String message) {
  // 리다이렉트된 페이지에서 플래시 속성을 사용
  System.out.println("Flash Message: " + message);
  return "anotherPage";     // anotherPage.mustache
 }

 @PostMapping("/api/person")
 public ResponseEntity<Person> person(@RequestBody Person person){
  return ResponseEntity.status(HttpStatus.OK).body(person);
 }

}
