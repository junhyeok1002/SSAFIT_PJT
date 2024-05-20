package com.ssafy.ssafit.model.dto;

import java.util.*;

//운동명, 한국이름, 사용 근육 부위(주동근, 강한 협응근, 약한 협응근)
public enum Fitness {
 // 팔운동(이두)
 Dumbbell_Curl(1, "덤벨 컬", Muscle.Biceps_Brachii,
         Arrays.asList(Muscle.Forearm),
         Arrays.asList(Muscle.Deltoids_Front)),
 Dumbbell_Seated_Curl(2, "덤벨 시티드 컬", Muscle.Biceps_Brachii,
         Arrays.asList(Muscle.Forearm),
         Arrays.asList(Muscle.Deltoids_Front)),
 Dumbbell_Seated_Preacher_Curl(3, "덤벨 시티드 프리처 컬", Muscle.Biceps_Brachii,
         Arrays.asList(),
         Arrays.asList(Muscle.Forearm)),
 Barbell_Curl(4, "바벨 컬", Muscle.Forearm,
         Arrays.asList(Muscle.Biceps_Brachii),
         Arrays.asList(Muscle.Deltoids_Front)),
 Barbell_Seated_Preacher_Curl(5, "바벨 시티드 프리처 컬", Muscle.Biceps_Brachii,
         Arrays.asList(),
         Arrays.asList(Muscle.Forearm)),

 Cable_Biceps_Curl(6, "케이블 바이셉스 컬", Muscle.Biceps_Brachii,
         Arrays.asList(),
         Arrays.asList(Muscle.Forearm)),

 Concentration_Dumbbell_Curl(7, "컨센트레이션 덤벨 컬", Muscle.Biceps_Brachii,
         Arrays.asList(),
         Arrays.asList(Muscle.Forearm)),

 Hammer_Curl(8, "해머 컬", Muscle.Forearm,
         Arrays.asList(Muscle.Biceps_Brachii),
         Arrays.asList(Muscle.Deltoids_Front)),

 // 어깨 운동
 Barbell_Shoulder_Press(9, "바벨 숄더 프레스", Muscle.Deltoids_Front,
         Arrays.asList(Muscle.Deltoids_Lateral),
         Arrays.asList(Muscle.Trapezius)),

 Dumbbell_Shoulder_Press(10 , "덤벨 숄더 프레스", Muscle.Deltoids_Front,
         Arrays.asList(Muscle.Deltoids_Lateral),
         Arrays.asList(Muscle.Trapezius)),

 Side_Lateral_Raise(11, "사이드 레터럴 레이즈", Muscle.Deltoids_Lateral,
         Arrays.asList(Muscle.Deltoids_Front),
         Arrays.asList(Muscle.Trapezius)),
 Front_Lateral_Raise(12, "프론트 레터럴 레이즈", Muscle.Deltoids_Front,
         Arrays.asList(),
         Arrays.asList(Muscle.Deltoids_Lateral, Muscle.Trapezius)),
 Machine_Shoulder_Press(13, "머신 숄더 프레스", Muscle.Deltoids_Front,
         Arrays.asList(Muscle.Deltoids_Lateral),
         Arrays.asList(Muscle.Trapezius)),
 One_Arm_Cable_Lateral_Raise(14, "원암 케이블 레터럴 레이즈", Muscle.Deltoids_Lateral,
         Arrays.asList(Muscle.Deltoids_Front),
         Arrays.asList(Muscle.Trapezius)),
 Two_Arm_Cable_Lateral_Raise(15, "투암 케이블 레터럴 레이즈", Muscle.Deltoids_Front,
         Arrays.asList(Muscle.Deltoids_Lateral),
         Arrays.asList(Muscle.Trapezius)),
 Bent_Over_Lateral_Raise(16, "벤트 오버 레터럴 레이즈", Muscle.Deltoids_Posterior,
         Arrays.asList(Muscle.Deltoids_Front, Muscle.Trapezius),
         Arrays.asList()),
 Seated_Bent_Over_Lateral_Raise(17, "시티드 벤트 오버 레터럴 레이즈", Muscle.Deltoids_Posterior,
         Arrays.asList(Muscle.Deltoids_Front, Muscle.Trapezius),
         Arrays.asList()),


 // 팔운동(삼두)
 Dumbbell_Lying_Triceps_Extension(18, "덤벨 라잉 트라이셉스 익스텐션", Muscle.Triceps_Brachii,
         Arrays.asList(),
         Arrays.asList(Muscle.Deltoids_Front)),
 Barbell_Lying_Triceps_Extension(19, "바벨 라잉 트라이셉스 익스텐션", Muscle.Triceps_Brachii,
         Arrays.asList(),
         Arrays.asList(Muscle.Deltoids_Front)),
 Dumbbell_Overhead_Triceps_Extension(20, "덤벨 오버헤드 트라이셉스 익스텐션", Muscle.Triceps_Brachii,
         Arrays.asList(),
         Arrays.asList()),
 Cable_Push_Down(21, "케이블 푸시 다운", Muscle.Triceps_Brachii,
         Arrays.asList(Muscle.Deltoids_Front),
         Arrays.asList(Muscle.Forearm)),
 Dips(22, "딥스", Muscle.Pectoralis_Major,
         Arrays.asList(Muscle.Triceps_Brachii),
         Arrays.asList(Muscle.Deltoids_Front)),
 Dumbbell_Kick_Back(23, "덤벨 킥백", Muscle.Triceps_Brachii,
         Arrays.asList(Muscle.Deltoids_Posterior),
         Arrays.asList()),
 Chair_Dips(24, "체어 딥스", Muscle.Triceps_Brachii,
         Arrays.asList(Muscle.Deltoids_Front),
         Arrays.asList(Muscle.Forearm, Muscle.Trapezius)),

 // 하체 운동
 Dumbbell_Squat(25, "덤벨 스쿼트", Muscle.Quadriceps_Femoris,
         Arrays.asList(Muscle.Gluteus),
         Arrays.asList(Muscle.Abdominal_Muscles)),
 Barbell_Squat(26, "바벨 스쿼트", Muscle.Quadriceps_Femoris,
         Arrays.asList(Muscle.Gluteus),
         Arrays.asList(Muscle.Abdominal_Muscles, Muscle.Hamstrings)),
 Front_Squat(27, "프론트 스쿼트", Muscle.Quadriceps_Femoris,
         Arrays.asList(Muscle.Gluteus),
         Arrays.asList(Muscle.Abdominal_Muscles)),
 Dumbbell_Lunge(28, "덤벨 런지", Muscle.Quadriceps_Femoris,
         Arrays.asList(Muscle.Gluteus),
         Arrays.asList(Muscle.Hamstrings)),
 Barbell_Lunge(29, "바벨 런지", Muscle.Quadriceps_Femoris,
         Arrays.asList(Muscle.Gluteus),
         Arrays.asList(Muscle.Hamstrings)),
 Leg_Extension(30, "레그 익스텐션", Muscle.Quadriceps_Femoris,
         Arrays.asList(),
         Arrays.asList()),
 Leg_Press(31, "레그 프레스", Muscle.Quadriceps_Femoris,
         Arrays.asList(Muscle.Hamstrings),
         Arrays.asList()),
 Leg_Curl(32, "레그 컬", Muscle.Hamstrings,
         Arrays.asList(),
         Arrays.asList(Muscle.Gluteus)),
 Dead_Lift(33, "데드 리프트", Muscle.Hamstrings,
         Arrays.asList(Muscle.Gluteus),
         Arrays.asList(Muscle.Latissimus_Dorsi)),
 Machine_Lunge(34, "머신 런지", Muscle.Quadriceps_Femoris,
         Arrays.asList(Muscle.Gluteus),
         Arrays.asList(Muscle.Hamstrings, Muscle.Abdominal_Muscles)),
 Machine_Squat(35, "머신 스쿼트", Muscle.Quadriceps_Femoris,
         Arrays.asList(Muscle.Gluteus),
         Arrays.asList(Muscle.Hamstrings, Muscle.Abdominal_Muscles)),
 Carf_Raise(36, "카프 레이즈", Muscle.Gastrocnemius,
         Arrays.asList(),
         Arrays.asList()),


 // 가슴 운동
 Dumbbell_Press(37, "덤벨 프레스", Muscle.Pectoralis_Major ,
                Arrays.asList(Muscle.Deltoids_Front, Muscle.Triceps_Brachii),
                 Arrays.asList()),
 Barbell_Press(38, "바벨 프레스", Muscle.Pectoralis_Major ,
            Arrays.asList(Muscle.Triceps_Brachii),
            Arrays.asList(Muscle.Deltoids_Front)),
 Machine_Press(39, "머신 프레스", Muscle.Pectoralis_Major ,
         Arrays.asList(Muscle.Deltoids_Front),
         Arrays.asList()),
 Dumbbell_Fly(40, "덤벨 플라이", Muscle.Pectoralis_Major ,
         Arrays.asList(Muscle.Deltoids_Front),
         Arrays.asList()),
 Cable_Fly(41, "케이블 플라이", Muscle.Pectoralis_Major ,
         Arrays.asList(Muscle.Deltoids_Front),
         Arrays.asList()),
 Machine_Fly(42, "머신 플라이", Muscle.Pectoralis_Major ,
         Arrays.asList(Muscle.Deltoids_Front),
         Arrays.asList()),
 Dumbbell_Pull_Over(43, "덤벨 풀오버", Muscle.Pectoralis_Major ,
         Arrays.asList(Muscle.Latissimus_Dorsi),
         Arrays.asList(Muscle.Abdominal_Muscles)),
 Barbell_Pull_Over(44, "바벨 풀오버", Muscle.Pectoralis_Major ,
         Arrays.asList(Muscle.Latissimus_Dorsi),
         Arrays.asList()),

 // 등 운동
 UnderGrip_Lat_Pull_Down(45, "언더 그립 렛풀다운", Muscle.Latissimus_Dorsi ,
         Arrays.asList(Muscle.Biceps_Brachii),
         Arrays.asList(Muscle.Forearm, Muscle.Trapezius)),
 OverGrip_Lat_Pull_Down(46, "오버 그립 렛풀다운", Muscle.Latissimus_Dorsi ,
         Arrays.asList(Muscle.Trapezius, Muscle.Deltoids_Posterior),
         Arrays.asList(Muscle.Forearm)),
 Overhead_Lat_Pull_Down(47, "오버 헤드 렛풀다운", Muscle.Latissimus_Dorsi ,
         Arrays.asList(Muscle.Trapezius),
         Arrays.asList(Muscle.Forearm)),
 Pull_Up(48, "풀업", Muscle.Latissimus_Dorsi ,
         Arrays.asList(Muscle.Trapezius),
         Arrays.asList(Muscle.Forearm, Muscle.Deltoids_Posterior, Muscle.Biceps_Brachii)),
 Arm_Pull_Down(49, "암풀다운", Muscle.Latissimus_Dorsi ,
         Arrays.asList(Muscle.Trapezius),
         Arrays.asList()),
 Cable_Seated_Row(50, "케이블 시티드 로우", Muscle.Latissimus_Dorsi ,
         Arrays.asList(Muscle.Trapezius, Muscle.Deltoids_Posterior),
         Arrays.asList(Muscle.Forearm)),
 One_Arm_Row(51, "원암로우", Muscle.Latissimus_Dorsi ,
         Arrays.asList(Muscle.Trapezius),
         Arrays.asList(Muscle.Deltoids_Posterior)),
 Dumbbell_Row(52, "덤벨 로우", Muscle.Latissimus_Dorsi ,
         Arrays.asList(Muscle.Trapezius, Muscle.Deltoids_Posterior),
         Arrays.asList()),
 T_Bar_Row(53, "티바 로우", Muscle.Latissimus_Dorsi ,
         Arrays.asList(Muscle.Trapezius, Muscle.Deltoids_Posterior),
         Arrays.asList()),
 Barbell_Row(54, "바벨 로우", Muscle.Latissimus_Dorsi ,
         Arrays.asList(Muscle.Trapezius, Muscle.Gluteus),
         Arrays.asList(Muscle.Forearm, Muscle.Quadriceps_Femoris)),
 Bent_Over_Barbell_Row(55, "벤트 오버 바벨 로우", Muscle.Latissimus_Dorsi ,
         Arrays.asList(Muscle.Trapezius, Muscle.Deltoids_Posterior),
         Arrays.asList(Muscle.Forearm));

 private final int id;
 private final String name;
 private final Muscle agonist;
 private final List<Muscle> synergists_first;
 private final List<Muscle> synergists_second;

 // 운동 한국이름, 주동근, 많이 돕는 협응근, 적게 돕는 협응근)
 private Fitness(int id, String name, Muscle agonist,
                 List<Muscle> synergists_first, List<Muscle> synergists_second) {
     this.id = id;
     this.name = name;
     this.agonist = agonist;
     this.synergists_first = synergists_first;
     this.synergists_second = synergists_second;
 }


 public int getId() { return id;}

 public String getName() {
     return name;
 }

 public Muscle getAgonist() {
     return agonist;
 }

 public List<Muscle> getSynergists_first() {
     return synergists_first;
 }

 public List<Muscle> getSynergists_second() {
     return synergists_second;
 }

 // 주동근은 1배, 강한 협응근은 0.5배, 약간 협응근은 0.25배 만큼 힘을 준다고 가정하고 연산
 public int getVolume() {
     int sum = agonist.getVolume();
     for(Muscle m : synergists_first) sum += (m.getVolume()/2);
     for(Muscle m : synergists_second) sum += (m.getVolume()/4);
     return sum;
 }

 public static int getEnumN(){
     return Fitness.values().length;
 }

 public Set activeMuscles(){
     Set<Muscle> set = new HashSet();

     // 주동근 담기
     set.add(agonist);

     // 협응근 담기
     set.addAll(synergists_first);
     set.addAll(synergists_second);

     return set;
 }
 
 

 
 
 public Map<String, ?> getMap(){
 	Map inner = new HashMap<>();
 	Map muscles = new HashMap<>();
 	
 	muscles.put("agonist", Arrays.asList(this.agonist));
 	muscles.put("synergists_first", this.synergists_first);
 	muscles.put("synergists_second", this.synergists_second);
 	
 	inner.put("id", this.id);
 	inner.put("name", this.name);
 	inner.put("muscle", muscles);
 	inner.put("e_name", this);
 	
 	return inner;
 }
 
 
 public static List<Map<String, ?>> getMaps(){
	List<Map<String, ?>> list = new ArrayList<>();
 	
	Fitness[] fitness = Fitness.values();
 	
 	for(Fitness f : fitness) {
 		list.add(f.getMap());
 	}
 	return list;
 }
 
 // 해당 근육을 주동근으로 사용하는 것
 public static List<Map<String, ?>> getMapsAgonist(String agonist){
	List<Map<String, ?>> list = new ArrayList<>();
 	
	Fitness[] fitness = Fitness.values();
 	
 	for(Fitness f : fitness) {
 		if(f.agonist.name().toLowerCase()
 		   .equals(agonist.toLowerCase()))
 			list.add(f.getMap());
 	}
 	return list;
 }
 
 // 해당 근육을 1차 협응근으로 사용하는 것
 public static List<Map<String, ?>> getMapsSynergyFirst(String synergists_first){
	List<Map<String, ?>> list = new ArrayList<>();
 	
	Fitness[] fitness = Fitness.values();
 	
 	for(Fitness f : fitness) {
 		for(Muscle m : f.synergists_first) {
 			if(m.name().toLowerCase()
 		 		   .equals(synergists_first.toLowerCase())) {
 				list.add(f.getMap());
 				break;
 			}
 		}
 	}
 	return list;
 }
 
//해당 근육을 1차 협응근으로 사용하는 것
 public static List<Map<String, ?>> getMapsSynergySecond(String synergists_second){
	List<Map<String, ?>> list = new ArrayList<>();
 	
	Fitness[] fitness = Fitness.values();
 	
 	for(Fitness f : fitness) {
 		for(Muscle m : f.synergists_second) {
 			if(m.name().toLowerCase()
 		 		   .equals(synergists_second.toLowerCase())) {
 				list.add(f.getMap());
 				break;
 			}
 		}
 	}
 	return list;
 }
 public static Fitness findFitness(String name) {
	 Fitness fitness = Fitness.valueOf(convertToCamelCase(name));
	 return fitness;
 }
 public static String convertToCamelCase(String snakeCase) {
     StringBuilder camelCase = new StringBuilder();
     boolean nextUpperCase = true;
     
     for (int i = 0; i < snakeCase.length(); i++) {
         char currentChar = snakeCase.charAt(i);
         
         if (currentChar == '_') {
             camelCase.append('_');
             nextUpperCase = true;
         } else {
             if (nextUpperCase) {
                 camelCase.append(Character.toUpperCase(currentChar));
                 nextUpperCase = false;
             } else {
                 camelCase.append(Character.toLowerCase(currentChar));
             }
         }
     }
     
     return camelCase.toString();
 }
 
}
