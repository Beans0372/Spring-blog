package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cos.blog.config.aop.RoleInterceptor;
import com.cos.blog.config.aop.SessionInterceptor;

// 필터링
@Configuration  // 스프링컨텍스트에 IOC 되는 것
//WebMvnConfigurer는 버전 업데이트되면 이름이 바뀔 수 도 있다.
public class WebConfig implements WebMvcConfigurer { 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// aop에 있는 인터셉터 등록 끝
		registry.addInterceptor(new SessionInterceptor())
		.addPathPatterns("/user/**")
		.addPathPatterns("/post/**")
		.addPathPatterns("/post**");
		
		registry.addInterceptor(new RoleInterceptor())
		.addPathPatterns("/admin/**");
	}
}