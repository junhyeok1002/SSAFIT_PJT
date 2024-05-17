package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Routine;

public interface RoutineService {
	//루틴 전체 조회
	public List<Routine> getRoutineList();
	
	// 루틴으로 그 루틴을 DB에서 가져옴 => id 가져오기 위함
	public Routine selectOneByRoutine(Routine routine);
	
	// id로 그 루틴을 가져옴
	public Routine selectOneById(int id);
	
	// 루틴 하나 등록
	public void createOne(Routine routine);
}
