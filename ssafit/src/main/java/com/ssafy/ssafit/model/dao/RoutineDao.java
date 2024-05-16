package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Routine;

public interface RoutineDao {
	// 전체 게시글을 조회
	public List<Routine> selectAll();
}
