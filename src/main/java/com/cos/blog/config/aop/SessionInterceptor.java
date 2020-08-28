package com.cos.blog.config.aop;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cos.blog.model.User;

// 인증 관리
// 내가 new 할 예정이라 메모리에 띄울 필요가 없다. (@를 안 쓴다는 뜻)
public class SessionInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 인증관리의 인터셉터
		// return 값이 true면 통과 false면 취소
		HttpSession session = request.getSession();
		
		User principal = (User) session.getAttribute("principal");
		if (principal == null) {
			// 브라우저에게 알려줘야 한다.
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('로그인이 필요합니다.');");
			out.print("location.href='/auth/loginForm';");
			out.print("</script>");
		}
		// 잘되어 있으면 true!
		return true;
	}
}