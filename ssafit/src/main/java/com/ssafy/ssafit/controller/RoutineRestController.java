package com.ssafy.ssafit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Fitness;
import com.ssafy.ssafit.model.dto.Muscle;
import com.ssafy.ssafit.model.dto.Routine;
import com.ssafy.ssafit.model.service.RoutineService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-routine")
//@CrossOrigin("*")
public class RoutineRestController {
	private final RoutineService routineService;
	
	@Autowired
	public RoutineRestController(RoutineService routineService) {
		this.routineService = routineService;
	}
	
	// 전체 조회
	@GetMapping("/routine")
	public ResponseEntity<?> readAllRoutine(){
		List<Routine> routines = routineService.getRoutineList(); // 검색조회
		System.out.println(routines.toString());
		
		if(routines == null || routines.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Routine>>(routines, HttpStatus.OK);
	}
	
	// 하나의 루틴 조회
	@GetMapping("/routine/{id}")
	public ResponseEntity<?> readOneRoutine(@PathVariable("id") int id){
		Routine routine = routineService.selectOneById(id); // 검색조회
		
		if(routine == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Routine>(routine, HttpStatus.OK);
	}
	
	// 하나의 루틴을 입력받으면 => 있으면 가져오고, 없으면 만들어서라도 가져와
//	[
//	   "Dumbbell_Curl",
//	   "Dumbbell_Seated_Curl"
//	]
	@PostMapping("/routine")
	public ResponseEntity<?> readOneRoutinebyPost(@RequestBody List<String> fitnessNames) {
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
        
        // 입력받은 루틴을 만들고, 만들어져있으면 그냥 무시됨
        // 그 루틴을 DB에서 읽어옴
        Routine temp = new Routine(fitnessList);
        routineService.createOne(temp);
        Routine routine = routineService.selectOneByRoutine(temp);
        

        return new ResponseEntity<Routine>(routine, HttpStatus.OK);
    }
	
	// 근육 부위를 불러오기
	@GetMapping("/muscle")
	public ResponseEntity<?> readAllMuscle(){
		List<Map<String, ?>> muscleList = Muscle.getMaps();
        return new ResponseEntity<List<Map<String, ?>>>(muscleList, HttpStatus.OK);
	}
	
	// 운동 불러오기
	@GetMapping("/fitness")
	public ResponseEntity<?> readAllFitness(){
		List<Map<String, ?>> fitnessList = Fitness.getMaps();
        return new ResponseEntity<List<Map<String, ?>>>(fitnessList, HttpStatus.OK);
	}
	
	@GetMapping("/fitness/{name}")
	public ResponseEntity<?> readAllFitness(@PathVariable("name") String name){
		Fitness f = Fitness.findFitness(name);
		Map<String, ?> fitness = f.getMap();
        return new ResponseEntity<Map<String, ?>>(fitness, HttpStatus.OK);
	}
	
	// 해당 근육을 주동근으로 사용하는 운동 불러오기
	@GetMapping("/fitness/agonist/{muscle}")
	public ResponseEntity<?> readAgonistFitness(@PathVariable("muscle") String muscle){
		List<Map<String, ?>> fitnessList = Fitness.getMapsAgonist(muscle);
        return new ResponseEntity<List<Map<String, ?>>>(fitnessList, HttpStatus.OK);
	}

	// 해당 근육을 1차 협응근으로 사용하는 운동 불러오기
	@GetMapping("/fitness/synergyfirst/{muscle}")
	public ResponseEntity<?> readSynergyFirstFitness(@PathVariable("muscle") String muscle){
		List<Map<String, ?>> fitnessList = Fitness.getMapsSynergyFirst(muscle);
        return new ResponseEntity<List<Map<String, ?>>>(fitnessList, HttpStatus.OK);
	}
	
	// 해당 근육을 2차 협응근으로 사용하는 운동 불러오기
	@GetMapping("/fitness/synergysecond/{muscle}")
	public ResponseEntity<?> readSynergySecondFitness(@PathVariable("muscle") String muscle){
		List<Map<String, ?>> fitnessList = Fitness.getMapsSynergySecond(muscle);
        return new ResponseEntity<List<Map<String, ?>>>(fitnessList, HttpStatus.OK);
	}

	// 해당 id에 해당하는 루틴을 
	@GetMapping("/fitness/routine/{id}")
	public ResponseEntity<?> readRoutine(@PathVariable("id") int id){
		Routine routine = routineService.selectOneById(id);
		
		Map<String, ?> routineList = routine.makeMap();
		System.out.println(routineList.toString());
		
        return new ResponseEntity<Map<String, ?>>(routineList, HttpStatus.OK);
	}
	
	// 해당 id에 해당하는 루틴을 
	@GetMapping("/fitness/workout/{routine_id}")
	public ResponseEntity<?> readRoutine(@PathVariable("routine_id") int routine_id, HttpSession session){
		Routine routine = routineService.selectOneById(routine_id);
		routine.init();
		
		// 세션에 루틴 저장
        session.setAttribute("routine", routine);
		
        Map map = routine.pageInfoBeforeSelect();
        
        System.out.println("여기서 운동하기 페이지로 이동");
        return new ResponseEntity<Map<String, ?>>(map, HttpStatus.OK);
	}
	
	@GetMapping("/fitness/workout/{routine_id}/{select_name}")
	public ResponseEntity<?> readRoutine(@PathVariable("routine_id") int routine_id, @PathVariable("select_name") String select_name, HttpSession session){
		Routine routine = (Routine) session.getAttribute("routine");

		routine.selectFitness(select_name);
        Map map = routine.pageInfoBeforeSelect();
        
        if(((List<Routine>) map.get("remain")).size() == 0) {
        	System.out.println("이제 끝이야!!! 후기 페이지로 넘어가줘");
        	List list = routine.getSorted();
        	return new ResponseEntity<List<Fitness>>(list, HttpStatus.OK);
        }

        return new ResponseEntity<Map<String, ?>>(map, HttpStatus.OK);
	}
}
