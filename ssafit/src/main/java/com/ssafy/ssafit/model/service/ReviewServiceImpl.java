package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.model.dao.ReviewDao;
import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.ReviewCondition;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	
	private ReviewDao reviewDao;
	
	//의존성 주입
	@Autowired
	public ReviewServiceImpl(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	@Override
	public List<Review> getReviewList() {
		// 모든 리뷰 가져오기
		return reviewDao.reviewSelectAll();
	}

	@Override
	public Review readReview(int reviewId) {
		// 특정 리뷰 상세 조회
		
		// 조회수 늘려주고 해당 리뷰 반환
		reviewDao.reviewUpdateViewCnt(reviewId);
		return reviewDao.reviewSelectOne(reviewId);
	}

	@Override
	@Transactional
	public int writeReview(Review review) {
		// 리뷰 작성
		return reviewDao.reviewInsert(review);
	}

	@Override
	@Transactional
	public int removeReview(int reviewId) {
		// 리뷰 삭제
		return reviewDao.reviewDelete(reviewId);
	}

	@Override
	@Transactional
	public int modifyReview(Review review) {
		// 리뷰 수정
		return reviewDao.reviewUpdate(review);
	}

	@Override
	public List<Review> searchReview(ReviewCondition reviewCondition) {
		// 리뷰 검색 및 정렬
		return reviewDao.searchReview(reviewCondition);
	}

	
}
