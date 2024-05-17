package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.User;

public interface UserDao {
	//db와 가깝에 작성
	
	// 회원 가입 전 중복 아이디가 존재하는지 확인
	User userExist(User user);
	
	// 회원 가입, 삽입에 가까움
	int userSignup(User user);
	
	// 로그인
	User userLogin(User user);
	

}
