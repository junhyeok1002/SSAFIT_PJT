package com.ssafy.ssafit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<?> readAllRoutine(){
		List<Routine> routines = routineService.getRoutineList(); // 검색조회
		
		if(routines == null || routines.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Routine>>(routines, HttpStatus.OK);
	}
	
	
//	[
//	   "Dumbbell_Curl",
//	   "Dumbbell_Seated_Curl"
//	]
	@PostMapping("/routine")
	public ResponseEntity<?> createRoutine(@RequestBody List<String> fitnessNames) {
		List<Fitness> fitnessList = new ArrayList<>();
		
        // 폼 데이터로 받은 문자열을 Fitness 열거형(enum)으로 변환하여 리스트에 추가
        for (String name : fitnessNames) {
            try {
                Fitness fitness = Fitness.valueOf(name);
                fitnessList.add(fitness);
            } catch (IllegalArgumentException e) {
                // 열거형(enum)에 해당하는 값이 없을 경우 처리할 내용
                System.out.println("Invalid fitness name: " + name);
            }
        }
        
        Routine routine = new Routine(fitnessList);
        return new ResponseEntity<Routine>(routine, HttpStatus.OK);
    }
	
}
