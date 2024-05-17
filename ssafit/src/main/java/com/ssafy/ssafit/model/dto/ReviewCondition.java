package com.ssafy.ssafit.model.dto;

public class ReviewCondition {
	private String reviewTitle; // 검색할 제목
	private String reviewAuthorName; // 작성자명
	private String reivewDirection; // 정렬 방향, null -> 보통, asc 오름차순, desc 내림차순
	
	ReviewCondition() {}

	public ReviewCondition(String reviewTitle, String reviewAuthorName, String reivewDirection) {
		super();
		this.reviewTitle = reviewTitle;
		this.reviewAuthorName = reviewAuthorName;
		this.reivewDirection = reivewDirection;
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

	public String getReivewDirection() {
		return reivewDirection;
	}

	public void setReivewDirection(String reivewDirection) {
		this.reivewDirection = reivewDirection;
	}

	@Override
	public String toString() {
		return "ReviewCondition [reviewTitle=" + reviewTitle + ", reviewAuthorName=" + reviewAuthorName
				+ ", reivewDirection=" + reivewDirection + "]";
	}
	
	

}
