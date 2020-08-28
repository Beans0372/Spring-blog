package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping({"","/"})
	public String TestIndex() {
		return "index";
	}
	
	// auth는 특이하게 여기서 처리함
	@GetMapping({"auth/joinForm"})
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping({"auth/loginForm"})
	public String loginForm() {
		return "user/loginForm";
	}
}