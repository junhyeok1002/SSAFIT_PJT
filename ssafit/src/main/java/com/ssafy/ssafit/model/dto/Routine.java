package com.ssafy.ssafit.model.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Routine {
	int id;
    List<Fitness> routine;
    String fitnessList;
    

    // init 이후 다루어질 변수들
    ArrayList<Fitness>[] graph;
    int[] inDegree;
    ArrayList<Fitness> queue;
    
    // 생성자
    public Routine() {
        this.routine = new ArrayList<>();
    }

    public Routine(List<Fitness> routine) {
        this.routine = routine;
    }
    
    
    // 루틴을 넣기
    public void putRoutine(Fitness fitness){
        this.routine.add(fitness);
    }

    // 루틴을 다 넣었으면 진입차수를 초기화하기
    public void init(){
        int N = Fitness.getEnumN()+1;

        // 루틴의 길이만큼 그래프를 만들기
        graph = new ArrayList[N];
        inDegree = new int[N];
        queue = new ArrayList<>();

        for(Fitness standard_fit : routine){
            for(Fitness near_fit : routine){
                // 자기 자신이면 넘어가기
                if(standard_fit.equals(near_fit)) continue;

                // 만약 현재 단계의 그래프가 비어있으면 생성하기
                if(graph[standard_fit.getId()] == null){
                    graph[standard_fit.getId()] = new ArrayList<>();
                }

                // 비교해서 진입차수, 그래프를 건드리기

                // 만약 교집합이 있으면
                Set standardSet = standard_fit.activeMuscles();
                Set nearSet = near_fit.activeMuscles();
                standardSet.retainAll(nearSet);

                // 교집합이 비어있다면 넘어감
                if(standardSet.isEmpty()) continue;;

                // 교집합이 있다면, 사용하는 근육이 겹친다는 의미이고 그렇다면 근육 총량을 비교하여 선후관계를 정해야함
                // 기준점의 볼륨이 더 크다면
                if(standard_fit.getVolume() > near_fit.getVolume()){
                    // 그래프에 다음 순서로 인접 피트니스를 넣기
                    graph[standard_fit.getId()].add(near_fit);

                    // 그 인접 피트니스의 진입차수를 증가시키기
                    inDegree[near_fit.getId()]++;
                }
                // 기준점보다 볼륨이 더 작거나 같다면 넘어가기 => 어처지 전부 돌게됨
            }
        }
    }
    
    @JsonIgnore
    // 선택지를 반환하기
    public List<Fitness> getSelection(){
        List<Fitness> selection = new ArrayList<>();

        for(Fitness fit : routine){
            // 진입 차수가 0이면 설렉션에 넣기
            if(inDegree[fit.getId()] == 0) selection.add(fit);
        }
        return selection;
    }

    // 선탁하기
    public void selectFitness(String fitness_name){
    	// 소문자 => 카멜케이스 변경 후, 처리
    	fitness_name = Fitness.convertToCamelCase(fitness_name);
    	Fitness fitness = Fitness.valueOf(fitness_name);
        // 선택된 것의 진입차수를 1낮추기, 이후에 진행되는 것들의 진입차수도 1낮추기
        inDegree[fitness.getId()]--;

        for(Fitness fit : graph[fitness.getId()]){
            inDegree[fit.getId()]--;
        }
        
        queue.add(fitness);
    }

    public List<Fitness> remainRoutine(){
        List<Fitness> remain = new ArrayList<>();

        for(Fitness fit : routine){
            // 진입 차수가 0이면 설렉션에 넣기
            if(inDegree[fit.getId()] >= 0) remain.add(fit);
        }
        return remain;
    }

    public List<Fitness> getRoutine() {
		return routine;
	}

	public void setRoutine(List<Fitness> routine) {
		this.routine = routine;
	}
		
	@JsonIgnore
	public String getFitnessList() {
		// 피트니스 리스트를 부를때 다시 오름차순 정렬 후, db에 넣기
		Collections.sort(routine);
		
		// id로 변환하여 숫자로 저장하기 => DB 길이를 줄이기 위함
		List<Integer> routineIDs = new ArrayList<>();
		for(Fitness fitness : routine) {
			routineIDs.add(fitness.getId());
		}
		
		
		this.fitnessList = routineIDs.toString();
		return fitnessList;
	}

	public void setFitnessList(String fitnessList) {
		this.fitnessList = fitnessList;
		
		List<Fitness> list = new ArrayList<>();
		
		String parseStr = fitnessList.substring(1, fitnessList.length() - 1);
		String[] temps = parseStr.split(", ");
		Fitness[] fitnesses = Fitness.values();
		
		for(String temp : temps) {
			list.add(fitnesses[Integer.parseInt(temp)-1]);
		}
	    this.routine = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	// 근활성도를 나타내는 코드
	public Map<String, Double> activate(){
		Map<String, Double> map = new HashMap<>();
		
		int total_volume = 0;
		
		for(Fitness fitness : routine) {
			// 주동근 계산
			// 없으면 만들고
			String agonist = fitness.getAgonist().name()+"("+fitness.getAgonist().getName()+")";
			if(!map.containsKey(agonist)) {
				map.put(agonist, (double)0);
			}
			map.put(agonist, map.get(agonist)+fitness.getAgonist().getVolume());
			total_volume += fitness.getAgonist().getVolume();
			
			// 1차 협응근
			for(Muscle muscle : fitness.getSynergists_first()) {
				String name = muscle.name()+"("+muscle.getName()+")";
				if(!map.containsKey(name)) {
					map.put(name, (double)0);
				}
				map.put(name, map.get(name)+(muscle.getVolume()/(double)2));
				total_volume += (muscle.getVolume()/(double)2);
			}
			
			// 2차 협응근
			for(Muscle muscle : fitness.getSynergists_second()) {
				String name = muscle.name()+"("+muscle.getName()+")";
				if(!map.containsKey(name)) {
					map.put(name, (double)0);
				}
				map.put(name, map.get(name)+(muscle.getVolume()/(double)4));
				total_volume += (muscle.getVolume()/(double)4);
			}
		}
		
		for(String key : map.keySet()) {
			double percent = map.get(key) / (double)total_volume;
			percent = Math.round((percent * 10000))/((double) 100);
			map.put(key, percent);
		}
		
		map.put("TOTAL", (double)total_volume);
		return map;
	}
	
	 public Map<String, ?> makeMap(){
	 	Map outer = new HashMap<>();

	 	outer.put("id", this.id);
	 	outer.put("routine", this.routine);
	 	outer.put("activation", this.activate());
	 	
	 	return outer;
	 }
	 
	 
	 public Map<String, ?> pageInfoBeforeSelect(){
		 Map outer = new HashMap<>();

		 outer.put("selection",changeMap(this.getSelection()));
		 outer.put("remain",changeMap(this.remainRoutine()));

		 return outer;
	 }
	 
	 public static List changeMap(List<Fitness> list){
		 List fMapList = new ArrayList<>(); 
		 
		 for(Fitness f : list) {
			 Map<String, ?> fMap = f.getMap();
			 fMapList.add(fMap);
		 }
			 
		 return fMapList;
	 }
	 
	 
	 @JsonIgnore
	 public List getSorted(){
		 List sortList = changeMap(this.queue);
		 return sortList;
	 }
	 
	@Override
	public String toString() {
		return "Routine [id=" + id + ", routine=" + routine + "]";
	}
}
