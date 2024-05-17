package com.ssafy.ssafit.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    // 문자열을 리스트로 바꿔주는 메서드
    public List<Integer> StringToList(String temp) {
    	// 현재 [1,2,3,4] 인 상태
    	// 원하는 형태는 [1,2,3,4]
    	List<Integer> result = new ArrayList<>();
    	temp = temp.substring(1,temp.length()-1); // [1,2,3,4] -> 1,2,3,4
    	for(String s : temp.split(",")) {
    		result.add(Integer.parseInt(s));
    	}

    	return result;
    }
    
	// 로그인 후 갖고 있는 fav문자열과 done문자열을 리스트화해서 정보 갱신해주기
    // 최초 로그인할 때만 한 번 사용하는 메서드
    public void getList(HttpSession session) {
    	User user = (User) session.getAttribute("login");
    	
    	// 둘다 문자열 상태
    	String fav = user.getFavorite(); // [1,2,3,4,5] 이렇게 된 상태임, DB엔 존재 X
    	String d = user.getDone();
    	System.out.println("fav : "+fav+" "+"d : "+d);
    	List<Integer> favList = StringToList(fav);
    	List<Integer> dList = StringToList(d);
    	System.out.println(favList);
    	System.out.println(dList);
    	

    	user.setFavoriteRoutine(favList);
    	user.setDoneRoutine(dList);
    	
    	// 갱신한 후 다시 session에 유저정보 갱신
    	session.setAttribute("login", user);
    }
    
    // 루틴 즐겨찾기 등록
    @PostMapping("/favorit/{id}")
    public ResponseEntity<?> addFavorit(@PathVariable("id") String id, HttpSession session) {
    	// 현재 로그인 돼 있는지 확인
    	User user = (User) session.getAttribute("login");
    	if (user == null) {
    		// 아직 로그인이 안됐음
    		return new ResponseEntity<String>("루틴을 즐겨찾기로 등록하고 싶으면 로그인 하세요.",HttpStatus.BAD_REQUEST);
    	} else {
    		// 로그인 된 상태
    		// 해당 유저가 갖고 있는 fav문자열 done문자열, fav리스트, done리스트 갱신해줘야함
    		// 문자열 만큼 더해주고, 리스트 만큼 추가해줌
    		// fav문자열과 done문자열은 db에도 존재하는 정보 -> 갱신 해줘야함
    		Set<Integer> check = new HashSet<>();
    		for (int i : user.getFavoriteRoutine()) {
    			check.add(i);
    		}
    		check.add(Integer.parseInt(id));
    		List<Integer> checkList = new ArrayList<Integer>(check);
    		System.out.println("현재 체크리스트 "+checkList);
    		user.setFavoriteRoutine(checkList);
    		user.setFavorite(checkList.toString());
    		session.setAttribute("login", user); // 세션 영역에 해당 유저 정보 갱신
    		// db에도 반영해줌
    		userService.update(user);
    		return new ResponseEntity<User>(user, HttpStatus.OK);
    	}
    }
    
    // 다한 루틴 등록하기
    @PostMapping("/done/{id}")
    public ResponseEntity<?> addDone(@PathVariable("id") String id, HttpSession session) {
    	// 현재 로그인 돼 있는지 확인
    	User user = (User) session.getAttribute("login");
    	if (user == null) {
    		// 아직 로그인이 안됐음
    		return new ResponseEntity<String>("로그인이 필요한 작업입니다.",HttpStatus.BAD_REQUEST);
    	} else {
    		// 로그인 된 상태
    		Set<Integer> check = new HashSet<>();
    		for (int i : user.getDoneRoutine()) {
    			check.add(i);
    		}
    		check.add(Integer.parseInt(id));
    		
    		List<Integer> asdf = new ArrayList<>(check);
    		//리스트 변수 갱신
    		user.setDoneRoutine(asdf);
    		//문자열 갱신
    		user.setDone(asdf.toString());
    		
    		
    		
    		session.setAttribute("login", user); // 세션 영역에 해당 유저 정보 갱신
    		// db에도 반영해줌
    		userService.update(user);
    		return new ResponseEntity<User>(user, HttpStatus.OK);
    		
    	}
    }
    
}
