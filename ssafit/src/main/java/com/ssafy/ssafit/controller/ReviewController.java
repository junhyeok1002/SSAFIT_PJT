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

import jakarta.servlet.http.HttpSession;


@RestController
//@CrossOrigin("*") // 우선 작동하는지 보기위해...
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
    	//진입과 동시에 바로 로그인을 했다고 가정함
    	
        List<Review> reviews = reviewService.getReviewList();
    	return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
    }


	// 특정 리뷰 상세 조회
    @GetMapping("/{reviewId}")
    public ResponseEntity<?> reviewDetail(@PathVariable("reviewId") int reviewId) {
    	Review review = reviewService.readReview(reviewId);
    	return new ResponseEntity<Review>(review, HttpStatus.OK);
    }
    

	// 리뷰 생성
    @PostMapping("")
    public ResponseEntity<?> reviewCreate(@RequestBody Review review, HttpSession session) {
    	//user의 id와 name은 temp객체에서 가져옴
    	System.out.println("유저의 정보 : "+session.getAttribute("login"));
    	System.out.println("넣기전의 모습 "+review.toString());
    	User user = (User) session.getAttribute("login");
    	review.setUserId(user.getId());
    	review.setUserName(user.getName());
		int result = reviewService.writeReview(review);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    // 리뷰 수정, 우선 수정할 권한이 있는지 확인이 필요함
    // 수정할 권한 1. 본인이 작성자, 2. 관리자
    @PutMapping("/{reviewId}")
    public ResponseEntity<?> reivewUpdate(@PathVariable("reviewId") int reviewId, @RequestBody Review review, HttpSession session) {
    	// 뭔가 인터셉터에서 해야할 것 같지만
    	// 우선은 여기에서 검증해보자!
    	System.out.println("여기는 수정하기!");
    	// 리뷰아이디에 해당하는 바뀌기전 원본 리뷰를 일단 가져와서
    	// RequestBody로 가져온, 내용을 갱신할 reivew와 비교
    	// userId를 비교해서 일치하면 수정할 수 있도록 한다!
    	Review temp = reviewService.readReview(reviewId); // 해당 reviewId를 가진, 아직 내용이 바뀌지 않은 리뷰
    	User user = (User) session.getAttribute("login"); // 현재 로그인 된 계정의 정보 
    	if (temp.getUserId().equals(user.getId()) || user.getId().equals("admin")) { // 일치하거나 관리자면 수정
    		review.setReviewId(reviewId);
    		int result = reviewService.modifyReview(review);
    		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);    		
    	} else {
    		System.out.println("작성자 불일치로 수정하지 않습니다.");
    		// 작성자 불일치, 수정 안해줌
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }


	// 리뷰삭제
    // 여기도 마찬가지 삭제 전에 현재 유저의 id와 게시글에 등록된 userID를 대조해서
    // 일치하면 삭제해줌
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> reviewDelete(@PathVariable("reviewId") int reviewId, HttpSession session) {
    	// 삭제 대상의 리뷰를 일단 가져와
    	Review temp = reviewService.readReview(reviewId);
    	User user = (User) session.getAttribute("login");
//    	System.out.println(temp.getUserId().equals(user.getId()) || user.getId().equals("admin") );
    	try {
    		//로그인 하긴 했는데 일치 or 불일치
    		boolean state = temp.getUserId().equals(user.getId()) || user.getId().equals("admin");
    		if (! state) {
    			System.out.println("작성자 불일치로 삭제하지 않습니다.");
    			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    		} else {
    			int result = reviewService.removeReview(reviewId);
    			return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);    			
    		}
    	} catch (Exception e) {
    		// 로그아웃상태라서 이 영역으로 들어옴
    		if (user == null) {
    			System.out.println("로그인 하십시오.");
    		} else {
    			System.out.println("작성자 불일치로 삭제 하지 않습니다.");    			
    		}
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

    }
    

	// 검색 조건으로 리뷰들 가져오기
//	@GetMapping("/search/{title}/{name}/{direction}") // 일단 잘 작동하는지 확인하기 위해 임시적으로 주소창에서 확인함...
    //public ResponseEntity<?> reviewSearch(@PathVariable("title") String title,@PathVariable("name") String name,@PathVariable("direction") String direction) {
    @GetMapping("/search")
	public ResponseEntity<?> reviewSearch(@ModelAttribute("condition") ReviewCondition condition) {
		List<Review> reviews = reviewService.searchReview(condition);
		return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);	
	}
}
