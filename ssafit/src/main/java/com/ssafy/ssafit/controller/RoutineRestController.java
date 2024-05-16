package com.ssafy.ssafit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Fitness;
import com.ssafy.ssafit.model.dto.Routine;
import com.ssafy.ssafit.model.service.RoutineService;

@RestController
@RequestMapping("/api-routine")
public class RoutineRestController {
	private final RoutineService routineService;
	
	@Autowired
	public RoutineRestController(RoutineService routineService) {
		this.routineService = routineService;
	}
	
	@GetMapping("/routine")
	public ResponseEntity<?> list(){
		List<Routine> routines = routineService.getRoutineList(); // 검색조회
		
		if(routines == null || routines.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Routine>>(routines, HttpStatus.OK);
	}
}
