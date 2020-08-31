package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.controller.dto.CommonRespDto;
import com.cos.blog.model.Post;
import com.cos.blog.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping("/post/saveForm")
	public String saveForm() {
		return "post/saveForm";
	}

	@PostMapping("/post") // JSON으로 받아야해서 @RequestBody!
	public @ResponseBody CommonRespDto<?> postPorc(@RequestBody Post requestPost) {
	// userId, title, content
	postService.글쓰기(requestPost);
	// 로그인 같은 경우는 select로 찾을 수도 못 찾을 수도 있어서 분기를 한다. 밑에 것이 여기선 의미가 없긴함
	return new CommonRespDto<String>(1, "글쓰기 성공");
	}
}