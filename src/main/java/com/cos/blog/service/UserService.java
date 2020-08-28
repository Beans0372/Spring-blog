package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository; // DI

	@Transactional
	public int 회원가입(User user) {
		// 원래는 Exception Handler를 사용해야한다.
		// try catch를 잡아두면 fail이 아니라 done으로 간다.
		// try catch를 안 하고 fail 발생시켜도 상관은 없지만 지금은 done으로 가는 방식을 사용.
		// 이것을 간단하게 처리할 수 있는 어노테이션이 있다. ->>???
		user.setRole("ROLE_USER");
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.getMessage();
			return -1;
		}
	}
	// readOnly = true가 필요한 이유
	@Transactional(readOnly = true)
	public User 로그인(User user) {
		User persisUser = userRepository.login(user);
		return persisUser;
	}
}