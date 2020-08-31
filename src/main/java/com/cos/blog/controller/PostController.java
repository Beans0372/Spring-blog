package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	// post관련된 것은 전부 다 인증 필요 한 것으로 만든다.
	@GetMapping("/posts")
	public String getPosts(Model model) {
		// model은 RequestDispatcher임 view까지 데이터를 끌고간다. -> 그러면 posts로 뿌리기만 하면됨
		model.addAttribute("posts",postService.목록보기());
		return "index";
	}

	// /post/안녕 => 오류 @PathVariable이 걸러준다.
	// ?주소 -> 쿼리스트링
	// post/{id} -> 파라매터를 받는 것
	@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model) {
		// USER랑 조인하기위해 DTO 생성
		model.addAttribute("postDetailRespDto", postService.상세보기(id));
		return "post/detail";
	}

	@DeleteMapping("/post/{id}")
	public @ResponseBody CommonRespDto<?> deleteById(@PathVariable int id) {
	postService.삭제하기(id);
	return new CommonRespDto<String>(1, "삭제 결과 성공");
	}

	@PutMapping("/post/{id}")
	public @ResponseBody CommonRespDto<?> update(@RequestBody Post post) {
		// 수정할 땐 data 넘김
		postService.수정하기(post);
		return new CommonRespDto<String>(1, "수정 결과 성공");
	}
}