package com.ssafy.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao= userDao;
	}
	
	// 회원 가입
	@Transactional
	@Override
	public int signup(User user) {
		return userDao.userSignup(user);
	}
	
	// 로그인
	@Override
	public User login(User user) {
		// 해당 유저객체를 집어넣어서
		// 데이터가 존재하는지 확인 후
		// 존재하지 않는다면 null값, 존재한다면 User객체 고대로 나올 것!
		return userDao.userLogin(user);
	}

	@Override
	public User exist(User user) {
		// 회원 가입 전, 아이디 중복검사
		return userDao.userExist(user);
	}

	@Override
	public int update(User user) {
		// 유저 정보 갱신
		return userDao.userUpdate(user);
	}

	
	
}
