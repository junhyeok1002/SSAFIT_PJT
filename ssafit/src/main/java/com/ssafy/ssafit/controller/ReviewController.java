package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.ReviewCondition;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.ReviewService;

@RestController
@CrossOrigin("*") // 우선 작동하는지 보기위해...
@RequestMapping("/review")
public class ReviewController {
	

	private ReviewService reviewService;
	
	//의존성 주입
	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	//리뷰 전부 가져오기, 가장 기본
    @GetMapping("")
    public ResponseEntity<?> reviewList() {
    	System.out.println("전부가져와!!!");
        List<Review> reviews = reviewService.getReviewList();
    	return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
    }


	// 특정 리뷰 상세 조회
    @GetMapping("/{reviewId}")
    public ResponseEntity<?> reviewDetail(@PathVariable int reviewId) {
    	System.out.println("왜안되지?");
    	System.out.println("하나만 가져와!!!"+reviewId);
    	Review review = reviewService.readReview(reviewId);
    	return new ResponseEntity<Review>(review, HttpStatus.OK);
    }
    

	// 리뷰 생성
    @PostMapping("")
    public ResponseEntity<?> reviewCreate(@RequestBody Review review) {
    	System.out.println("만들어줘잉 "+review.getTitle());
		int result = reviewService.writeReview(review);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    // 리뷰 수정, 우선 수정할 권한이 있는지 확인이 필요함
    // 수정할 권한 1. 본인이 작성자, 2. 관리자
    // 일단 관리자는 뺀 상태, 추가하려면 RequestBody Reivew의 userId가 admin일때로 생각하면 될듯...?
    @PutMapping("/{reviewId}")
    public ResponseEntity<?> update(@PathVariable int reviewId, @RequestBody Review review) {
    	// 뭔가 인터셉터에서 해야할 것 같지만
    	// 우선은 여기에서 검증해보자!
    	
    	// 리뷰아이디에 해당하는 바뀌기전 원본 리뷰를 일단 가져와서
    	// RequestBody로 가져온, 내용을 갱신할 reivew와 비교
    	// userId를 비교해서 일치하면 수정할 수 있도록 한다!
    	Review temp = reviewService.readReview(reviewId); // 해당 reviewId를 가진, 아직 내용이 바뀌지 않은 리뷰
    	if (temp.getUserId() == review.getUserId()) { // 일치하면 수정을 하고
    		review.setReviewId(reviewId);
    		int result = reviewService.modifyReview(review);
    		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);    		
    	} else {
    		// 작성자 불일치
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }


	// 리뷰삭제
    // 여기도 마찬가지 삭제 전에 현재 유저의 id와 게시글에 등록된 userID를 대조해서
    // 일치하면 삭제해줌
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> reviewDelete(@PathVariable int reviewId) {
    	// 삭제 대상의 리뷰를 일단 가져와
    	Review temp = reviewService.readReview(reviewId);
    	
    	// User 객체를 따로 생성해서, User의 id랑 비교....
    	// 하려고했으나 getter setter가 없음...!
    	// 그냥 문자열로 확인하자!
    	
    	if (temp.getUserId().equals("ssafy")) { // 일단 id가 ssafy면 일치, 나머진 불일치
    		int result = reviewService.removeReview(reviewId);
    		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);    		
    	} else {
    		// 불일치, 삭제 못함
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);    		
    	}
    }
    

	// 검색 조건으로 리뷰들 가져오기
	@GetMapping("/search")
	public ResponseEntity<?> reviewSearch(@ModelAttribute("reviewCondition") ReviewCondition reviewCondition) {
		List<Review> reviews = reviewService.searchReview(reviewCondition);
		return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);	
	}
}
