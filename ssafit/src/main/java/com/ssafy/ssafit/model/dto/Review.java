package com.ssafy.ssafit.model.dto;

public class Review {
	private String title;
	private String userId;
	private String userName;
	private String createTime;
	private String routine;
	private String content;
	private int viewCnt;
	
	// 기본 생성자
	Review () {}
	
	// 운동 완료 후 후기 작성시(본인이 루틴 생성)
	
	

	// 모든 파라미터 받는 생성자
	public Review(String title, String userId, String userName, String createTime, String routine, String content,
			int viewCnt) {
		super();
		this.title = title;
		this.userId = userId;
		this.userName = userName;
		this.createTime = createTime;
		this.routine = routine;
		this.content = content;
		this.viewCnt = viewCnt;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getRoutine() {
		return routine;
	}


	public void setRoutine(String routine) {
		this.routine = routine;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getViewCnt() {
		return viewCnt;
	}


	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	
}
