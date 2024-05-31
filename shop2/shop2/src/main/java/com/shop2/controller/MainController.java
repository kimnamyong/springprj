package com.shop2.controller;

import com.shop2.dto.ItemSearchDto;
import com.shop2.dto.MainItemDto;
import com.shop2.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

 private final MainService mainService;



 @GetMapping(value = "/")
 public String main(ItemSearchDto itemSearchDto, Optional<Integer> page,
                    Model model, @AuthenticationPrincipal UserDetails user){

  Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);

  Page<MainItemDto> items = mainService.getMainItemPage(itemSearchDto, pageable);

  model.addAttribute("items", items);
  model.addAttribute("itemSearchDto", itemSearchDto);
  model.addAttribute("maxPage", 5);

  model.addAttribute("principal",user);

  log.info("user:{}",user);

  return "main";
 }

}