package com.bbs1.controller;

import com.bbs1.model.Pagination;
import com.bbs1.model.UserEdit;
import com.bbs1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
@Slf4j
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class UserController {

// @Autowired
// UserService userService;
 private  final UserService userService;

// public UserController(UserService userService) {
//  this.userService = userService;
// }

 @Secured("ROLE_ADMIN")
 @GetMapping("list")
 public String list(Model model, Pagination pagination) {
  model.addAttribute("users", userService.findAll(pagination));
  model.addAttribute("orderOptions", userService.getOrderOptions());
  model.addAttribute("searchOptions", userService.getSearchOptions());
  return "user/list";
 }
 @GetMapping("edit")
 public String edit(Model model, Pagination pagination, int id) {
  model.addAttribute("userEdit", userService.findById(id));
  return "user/edit";
 }
 @PostMapping(value="edit", params="cmd=save")
 public String edit(Model model, Pagination pagination,
                    @Valid UserEdit userEdit, BindingResult bindingResult) {
  try {
   userService.update(userEdit, bindingResult);
   return "redirect:list?" + pagination.getQueryString();
  }
  catch (Exception ex) {
   log.error("사용자 수정 에러", ex);
   bindingResult.rejectValue("", null, "수정할 수 없습니다.");
   return "user/edit";
  }
 }
 @PostMapping(value="edit", params="cmd=delete")
 public String delete(Model model, Pagination pagination,
                      UserEdit userEdit, BindingResult bindingResult) {
  try {
   userService.delete(userEdit.getId());
   return "redirect:list?" + pagination.getQueryString();
  }
  catch (Exception ex) {
   log.error("사용자 수정 에러", ex);
   bindingResult.rejectValue("", null, "삭제할 수 없습니다.");
   return "user/edit";
  }
 }


}
