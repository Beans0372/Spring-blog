package com.cos.blog.controller;

import javax.servlet.http.HttpSession;

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
	
	@GetMapping("auth/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		//여기서 index를 또 return하지말고 이미 만들어진곳을 가면된다.
		return "redirect:/";
	}
}