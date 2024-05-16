package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Routine;

public interface RoutineService {
	//게시글 전체 조회
	public List<Routine> getRoutineList();
}
