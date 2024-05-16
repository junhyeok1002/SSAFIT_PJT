package com.ssafy.ssafit.model.dto;

public class User {
	private String id;
	private String password;
	private String name;
	private String address;
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


