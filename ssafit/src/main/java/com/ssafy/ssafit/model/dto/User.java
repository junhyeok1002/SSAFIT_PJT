package com.ssafy.ssafit.model.dto;

import java.util.List;

public class User {
	private String id;
	private String password;
	private String name;
	private String address;
	
	List<Integer> favoriteRoutine;
	List<Integer> doneRoutine;
	private String favorite;
	private String done;
	
	public User() {
	}
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public User(String id, String password, String name, String address, String favorite, String done) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
	}
	
	
	
}


