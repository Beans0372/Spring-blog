package com.cos.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.controller.dto.CommonRespDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/auth/joinProc")
	public @ResponseBody CommonRespDto<?> joinProc(@RequestBody User user) {
		// joinProc(User user) 로만 하면 post를 통해 key, value 쌍으로 넘겨진 x-www-form-urlencoded 타입 데이터만 처리 가능
		// @RequestBody를 앞에 붙이면 메세지 컨버터 발동하여 json 타입으로 받고 처리 가능
		// web.xml에 스프링 필터 .. 중에 MessageConverter  -> 기본적으로는 무조건 x-www-form-urlencoded 타입 데이터가 들어올 거라 생각하고 있음
		// @RequestBody가 붙어 있으면(json 데이터가 들어오면) MessageConverter를 JsonConverter로 교체함

		userService.회원가입(user);

		return new CommonRespDto<String>(1, "회원가입 성공");	// 스프링에는 ResponseEntity라는 클래스를 미리 만들어 놓아서 이걸 사용하는 게 원칙임!
	}

	@PostMapping("/auth/loginProc")
	public @ResponseBody CommonRespDto<?> loginProc(@RequestBody User user, HttpSession session) {	// session에 대한 DI는 자동으로 이루어짐
		// MyBatis는 해당 아이디와 비밀번호로 일치하는 사용자가 있는 경우 User 타입 객체를 만들어 반환하고
		// 일치하는 사용자가 없는 경우는 null 값을 반환하고 있음
		User persistUser = userService.로그인(user);

		if (persistUser == null) {
			System.out.println("persistUser == null");
			return new CommonRespDto<String>(-1,"로그인 결과 실패");

		} else {
			System.out.println("persistUser != null");
			// 세션등록 해야함
			session.setAttribute("principal", persistUser);
			return new CommonRespDto<String>(1,"로그인 결과 성공");
		}
	}

	@GetMapping("/user/update")
	public String updateUserInfo() {
		return "/user/updateForm";
	}

	@PostMapping("/user/updateProc")
	public @ResponseBody CommonRespDto<?> updateUserInfoProc(@RequestBody User user) {
		userService.회원정보수정(user);
		return new CommonRespDto<String>(1, "회원정보 수정 성공");
	}
}