package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Routine;

public interface RoutineService {
	//루틴 전체 조회
	public List<Routine> getRoutineList();
	
	// 루틴 하나 등록
	public void createOne(Routine routine);
}
