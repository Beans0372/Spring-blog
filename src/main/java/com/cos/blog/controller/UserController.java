package com.cos.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.controller.dto.CommonRespDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@Controller
public class UserController {

	//ResponseBody는 응답을 html이 아니라 data로 하기 위해서
	// RequestBody를 붙여야 JSON data로 받을 수 있다. 안 붙이면 x-www-form-urlencoded만 받을 수 있음
	// Spring에서는 web.xml에서 필터링을 해야하는데 스프링 필터를 등록(기본적으로 필터링해야될것)해두면
	// 스프링필터에 ip 등등 많은데 MessageConverter가 있다. key=vlaue만 파싱하려고 대기하고 있는데
	// @RequestBody를 걸면 컨텍트 타입을 확인하고 JSON을 보면 MessageConverter로 change함
	// (ex : Object Mapper=jackson) 자바오브젝트로 변경해 줌

	@Autowired
	private UserService userService;

	@PostMapping("/auth/joinProc")
	public @ResponseBody CommonRespDto<?> joinProc(@RequestBody User user) {
		//?는 아직 정해지지 않음, 많이 씀
		int result = userService.회원가입(user);
		//ok가 아니라 응답 CommonRespDto 엔티티를 만들것이다.
		//사실 스프링에 만들어져 있지만 이해를 돕기위해서 만들었음
		//스프링에 들고 있는건 ResponseEntity<?>,return ResponseEntity<String>("1",HttpStatus.OK); OK를 컨트롤+클릭 
		return new CommonRespDto<String>(result, "회원가입 결과 : "+result); //분기를 타서 1을 넣어야하는거 아닌가
	}

	@PostMapping("/auth/loginProc")
	public @ResponseBody CommonRespDto<?> loginProc(@RequestBody User user, HttpSession session){
		User persistUser = userService.로그인(user);
		
		if(ObjectUtils.isEmpty(persistUser)) {
			//못찾으면 null이 아니라 빈객체를 return 해주기 때문이다.
			return new CommonRespDto<String>(-1, "로그인 결과 실패");
		}else {
			//세션 등록
			//request를 통해서 session에 접근할수있는 통로를 가지고 있는것이다.
			session.setAttribute("principal", persistUser);
			return new CommonRespDto<String>(1, "로그인 결과 성공");
		}
	}
}