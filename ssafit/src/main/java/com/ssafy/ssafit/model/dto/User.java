package com.ssafy.ssafit.model.dto;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String id;
	private String password;
	private String name;
	private String address;
	
	List<Integer> favoriteRoutine; // DB에는 정보가 없는 상태, favorite의 문자열 상태를 적절히 변환해서 루틴의 아이디리스트로 변환해서 집어넣을 공간
	List<Integer> doneRoutine; // 이것도 마찬가지
	private String favorite; // 운동의 구성들을 아이디상태로 갖고있고, 문자열 상태임
	private String done;
	
	public User() {
	}
	
	// 로그인 시 필요한 생성자
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	// 회원가입시 필요한 생성자
	public User(String id, String password, String name, String address) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
	}
	
	//유저 정보 갱신 생성자
	public User(String id, String password, String name, String address, String favorite, String done) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.favorite = favorite;
		this.done = done;
	}
	
	// 모든 파라미터를 받는 생성자
	public User(String id, String password, String name, String address, List<Integer> favoriteRoutine,
			List<Integer> doneRoutine, String favorite, String done) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.favoriteRoutine = favoriteRoutine;
		this.doneRoutine = doneRoutine;
		this.favorite = favorite;
		this.done = done;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Integer> getFavoriteRoutine() {
		return favoriteRoutine;
	}

	public void setFavoriteRoutine(List<Integer> favoriteRoutine) {
		this.favoriteRoutine = favoriteRoutine;
	}

	public List<Integer> getDoneRoutine() {
		return doneRoutine;
	}

	public void setDoneRoutine(List<Integer> doneRoutine) {
		this.doneRoutine = doneRoutine;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address
				+ ", favoriteRoutine=" + favoriteRoutine + ", doneRoutine=" + doneRoutine + ", favorite=" + favorite
				+ ", done=" + done + "]";
	}
	

	
}


