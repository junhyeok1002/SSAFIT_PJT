package com.ssafy.ssafit.model.dto;

public class ReviewCondition {
	private String reviewTitle = "none"; // 검색할 제목
	private String reviewAuthorName= "none"; // 작성자명
	private String reviewDirection= "none"; // 정렬 방향, null -> 보통, asc 오름차순, desc 내림차순
	private int reviewRoutineId = 0; // 특정 루틴을 다루는 게시글만 검색
	

	
	public ReviewCondition() {}



	public ReviewCondition(String reviewTitle, String reviewAuthorName, String reviewDirection, int reviewRoutineId) {
		super();
		this.reviewTitle = reviewTitle;
		this.reviewAuthorName = reviewAuthorName;
		this.reviewDirection = reviewDirection;
		this.reviewRoutineId = reviewRoutineId;
	}



	public String getReviewTitle() {
		return reviewTitle;
	}



	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}



	public String getReviewAuthorName() {
		return reviewAuthorName;
	}



	public void setReviewAuthorName(String reviewAuthorName) {
		this.reviewAuthorName = reviewAuthorName;
	}



	public String getReviewDirection() {
		return reviewDirection;
	}



	public void setReviewDirection(String reviewDirection) {
		this.reviewDirection = reviewDirection;
	}



	public int getReviewRoutineId() {
		return reviewRoutineId;
	}



	public void setReviewRoutineId(int reviewRoutineId) {
		this.reviewRoutineId = reviewRoutineId;
	}



	@Override
	public String toString() {
		return "ReviewCondition [reviewTitle=" + reviewTitle + ", reviewAuthorName=" + reviewAuthorName
				+ ", reviewDirection=" + reviewDirection + ", reviewRoutineId=" + reviewRoutineId + "]";
	}

	
	
	
}
