package com.ssafy.ssafit.model.dto;

public class Review {
	private int reviewId;
	private String title;
	private String userId;
	private String userName;
	private String createTime;
	private String routine;
	private String content;
	private int viewCnt;
	
	//기본 생성자
	Review() {}
	
	
	// 리뷰 생성을 위한 생성자
	public Review(String title, String userId, String userName, String content, String routine) {
		super();
		this.title = title;
		this.userId = userId;
		this.userName = userName;
		this.content = content;
		this.routine = routine;
	}
	
	//로그인시 리뷰 생성자를 위한 생성자
	public Review(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	

	// 리뷰 수정을 위한 생성자
	public Review(int reviewId, String title, String content) {
		super();
		this.reviewId = reviewId;
		this.title = title;
		this.content = content;
	}
	
	


	// 모든 파라미터를 받는 생성자
	public Review(int reviewId, String title, String userId, String userName, String createTime, String routine,
			String content, int viewCnt) {
		super();
		this.reviewId = reviewId;
		this.title = title;
		this.userId = userId;
		this.userName = userName;
		this.createTime = createTime;
		this.routine = routine;
		this.content = content;
		this.viewCnt = viewCnt;
	}




	public int getReviewId() {
		return reviewId;
	}


	


	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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


	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", title=" + title + ", userId=" + userId + ", userName=" + userName
				+ ", createTime=" + createTime + ", routine=" + routine + ", content=" + content + ", viewCnt="
				+ viewCnt + "]";
	}

	
}
