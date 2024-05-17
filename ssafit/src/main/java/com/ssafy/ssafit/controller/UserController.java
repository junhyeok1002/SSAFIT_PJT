package com.ssafy.ssafit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("")
public class UserController {
	
	private UserService userService;
	
	//의존성 주입
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//로그인 하기
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user,HttpSession session) {
    	//진입과 동시에 바로 로그인을 했다고 가정함
    	
    	// db에 해당 계정 정보가 있으면 객체가 반환
    	// 없으면 null값
    	try {
    		User result = userService.login(user);
    		System.out.println("결과는? "+result.toString());
    		//객체가 존재! == DB에 등록된 계정임, 세션에 해당 정보를 저장
    		
    		
    		
    		//일단 루틴을 문자열로 받자!
    		// 추후 프론트에서 데이터를 받아 삭제할 영역
    		result.setFavorite("[1,2,3,4,5]");
    		result.setDone("[1,2,3]");
    		session.setAttribute("login", result);
    		
    		
    		
    		
    		getList(session);
    		//리스트화 완료
    		System.out.println("리스트화 했어!");
    		User wind = (User) session.getAttribute("login");
    		System.out.println(wind.getDoneRoutine());
    		System.out.println(wind.getFavoriteRoutine());
    		
    		
    		return new ResponseEntity<User>(result,HttpStatus.OK);
    	} catch (Exception e) {
			// 해당 계정이 존재하지 않아 null값, 에러메세지를 반환
    		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    }
    
    // 회원가입 하기
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
    	//일단 가입까진 하는데, 해당 아이디가 이미 존재한다면 승인X
    	System.out.println("가입해줭");
    	try { 
    		User temp = userService.exist(user);
    		// 서비스가 작동하고, 여전히 이 영역인거면 아이디가 존재하는 것!
    		// 가입 안시켜줌...
    		System.out.println("결과는? "+temp.toString());
    		return new ResponseEntity<String>("아이디가 중복됩니다.", HttpStatus.BAD_REQUEST);
    	} catch (Exception e) {
			// 이 영역으로 와야 가입해도 되는거임!, 중복된 아이디가 없음!
    		int result = userService.signup(user);
    		System.out.println("가입할 예정 "+user.toString());
    		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		}

    }
    
    //로그아웃 하기
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
    	// 로그아웃은 세션영역 날리기
    	session.invalidate();
    	System.out.println("로그아웃 완료!");
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
	//로그인 후 갖고 있는 fav문자열과 done문자열을 리스트화해서 정보 갱신해주기
    public void getList(HttpSession session) {
    	User user = (User) session.getAttribute("login");
    	
    	// 둘다 문자열 상태
    	String fav = user.getFavorite(); // [1,2,3,4,5] 이렇게 된 상태임, DB엔 존재 X
    	String d = user.getDone();
    	System.out.println("fav : "+fav+" "+"d : "+d);
    	
    	fav = fav.substring(1, fav.length()-1);
    	d = d.substring(1,d.length()-1);
    	System.out.println("바꾸고싶다 "+fav+" "+d);
    	List<Integer> favList = new ArrayList<>();
    	List<Integer> dList = new ArrayList<>();
    	for (String s : fav.split(",")) {
    		favList.add(Integer.parseInt(s));
    	}
    	for (String s : d.split(",")) {
    		dList.add(Integer.parseInt(s));
    	}
    	user.setFavoriteRoutine(favList);
    	user.setDoneRoutine(dList);
    	
    	// 갱신한 후 다시 session에 유저정보 갱신
    	session.setAttribute("login", user);
    }
    
    // 루틴 즐겨찾기 등록
    
    
    // 다한 루틴 등록하기
    
}
