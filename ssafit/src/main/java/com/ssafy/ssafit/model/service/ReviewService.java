package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.ReviewCondition;

public interface ReviewService {
	// spring에 더 친숙하게
	
	//리뷰 전체 조회
	public List<Review> getReviewList();
	
	//리뷰 상세 조회
	public Review readReview(int reviewId); 
	
	//리뷰 작성
	public int writeReview(Review review);
	
	//리뷰 삭제 
	public int removeReview(int reviewId);
	
	//리뷰 수정
	public int modifyReview(Review review);
	
	//리뷰 검색
	public List<Review> searchReview(ReviewCondition reviewCondition);
}
