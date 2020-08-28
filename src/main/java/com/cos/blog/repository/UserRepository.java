package com.cos.blog.repository;

import com.cos.blog.model.User;

// mapper 스캔이 안되면 어노테이션을 붙여야함
public interface UserRepository {

	// 퍼시스턴스를 만들어 줌
	public int save(User user);
	public User login(User user);

	// 내부적으로 구현되어있다.
}