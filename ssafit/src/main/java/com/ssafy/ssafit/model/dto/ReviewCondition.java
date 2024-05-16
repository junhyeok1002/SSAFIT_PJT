package com.ssafy.ssafit.model.dto;

public class ReviewCondition {
	private String reviewTitle; // 검색할 제목
	private String reviewAuthorId; // 작성자ID (동명이인 피하기)
	private String reivewDirection; // 정렬 방향, null -> 보통, asc 오름차순, desc 내림차순
	
	ReviewCondition() {}

	public ReviewCondition(String reviewTitle, String reviewAuthorId, String reivewDirection) {
		super();
		this.reviewTitle = reviewTitle;
		this.reviewAuthorId = reviewAuthorId;
		this.reivewDirection = reivewDirection;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewAuthorId() {
		return reviewAuthorId;
	}

	public void setReviewAuthorId(String reviewAuthorId) {
		this.reviewAuthorId = reviewAuthorId;
	}

	public String getReivewDirection() {
		return reivewDirection;
	}

	public void setReivewDirection(String reivewDirection) {
		this.reivewDirection = reivewDirection;
	}

}
