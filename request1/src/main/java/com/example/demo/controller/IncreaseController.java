package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IncreaseController {
	private int count=0;
	
	@GetMapping("homework/increase")
	public String increase1(Model model) {	
		model.addAttribute("count",count);
		return "homework/test1";
	}
	
	@PostMapping("homework/increase")
	public String increase2(Model model) {
		count++;
		model.addAttribute("count",count);
		return "homework/test1";
	}
	
	
//	@GetMapping("homework/test2")
//	public String test2(Model model) {
//		model.addAttribute("num","one");
//		return "homework/test2";
//	}
		
	@GetMapping("homework/test2")
	public String test2(@RequestParam(name="num",defaultValue="one") String num,Model model) {
		model.addAttribute("num","one");
		return "homework/test2";
	}
		
	@PostMapping("homework/test2")
	public String test2(Model model, String num) {
		model.addAttribute("num",num);
		return "homework/test2";
	}
	
}
