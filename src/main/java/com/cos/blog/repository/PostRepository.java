package com.cos.blog.repository;

import java.util.List;

import com.cos.blog.controller.dto.PostDetailRespDto;
import com.cos.blog.model.Post;

// mapper 스캔이 안되면 어노테이션을 붙여야함
public interface PostRepository {
	// 퍼시스턴스를 만들어줌
	public void save(Post post);
	public List<Post> findAll();
	public PostDetailRespDto findById(int id);
	public void deleteById(int id);
}