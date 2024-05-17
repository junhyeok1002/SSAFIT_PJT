package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Routine;

public interface RoutineDao {
	// 전체 루틴을 조회
	public List<Routine> selectAll();
	
	// 루틴으로 그 루틴을 DB에서 가져옴 => id 가져오기 위함
	public Routine selectOneByRoutine(Routine routine);
	
	// id로 그 루틴을 가져옴
	public Routine selectOneById(int id);
	
	// 루틴하나를 등록
	public void createOne(Routine routine);
	
	
}
