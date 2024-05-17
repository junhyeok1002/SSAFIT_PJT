package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.User;

public interface UserService {
	// 회원 가입
	int signup(User user);
	
	// 회원 가입 전 아이디 중복검사
	User exist(User user);
	
	// 로그인
	User login(User user);
	
	// 유저 정보 갱신
	int update(User user);
	
}
