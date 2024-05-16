package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.ReviewCondition;


public interface ReviewDao {
	// db와 가깝게 작성
	
	// 루틴 리뷰 전부 가져오기
	List<Review> reviewSelectAll();
	
	// 특정 루틴 리뷰 가져오기
	Review reviewSelectOne(int reviewId);
	
	// 리뷰 작성하기
	int reviewInsert(Review review);
	
	//리뷰 수정
	int reviewUpdate(Review review);
	
	//리뷰 삭제
	int reviewDelete(int reviewId);
	
	//리뷰 조회수 증가
	int reviewUpdateViewCnt(int reviewId);
	
	//리뷰 검색 기능
	List<Review> searchReview(ReviewCondition reviewCondition);

}
