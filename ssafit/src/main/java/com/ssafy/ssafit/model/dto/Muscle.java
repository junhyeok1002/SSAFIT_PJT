package com.ssafy.ssafit.model.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Muscle {
    // 운동 부위 enum ⇒ 근육부피, 한글이름
    // volume의 단위는 cm^3
    Deltoids_Front(150, "삼각근 (전면)"),
    Deltoids_Lateral(120, "삼각근 (측면)"),
    Deltoids_Posterior(90, "삼각근 (후면)"),
    Pectoralis_Major(260, "대흉근"),
    Triceps_Brachii(237, "상완근 (삼두근)"),
    Latissimus_Dorsi(244, "광배근"),
    Trapezius(217, "승모근"),
    Biceps_Brachii(112, "이두근"),
    Forearm(65, "전완근"),
    Abdominal_Muscles(300, "복근"),
    Quadriceps_Femoris(1791, "대퇴사두 (허벅지 앞)"),
    Hamstrings(724, "햄스트링 (허벅지 뒤)"),
    Gluteus(1203, "둔근 (엉덩이)"),
    Gastrocnemius(849, "비복근 (종아리)");


    private final int volume;
    private final String name;

    private Muscle(int volume, String name) {
        this.volume = volume;
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }
    public String getName() {
        return name;
    }
    
    public Map<String, ?> getMap(){
    	Map inner = new HashMap<>();
    	
    	inner.put("name", this.name);
    	inner.put("volume", this.volume);
    	inner.put("e_name", this);
    	return inner;
    }
    
    public static List<Map<String, ?>> getMaps(){
    	List<Map<String, ?>> list = new ArrayList<>();
    	
    	Muscle[] muscles = Muscle.values();
    	
    	for(Muscle m : muscles) {
    		list.add(m.getMap());
    	}
    	return list;
    }
}
