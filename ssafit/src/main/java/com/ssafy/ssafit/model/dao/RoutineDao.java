package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Routine;

public interface RoutineDao {
	// 전체 루틴을 조회
	public List<Routine> selectAll();
	
	// 루틴하나를 등록
	public void createOne(Routine routine);
}
