package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.RoutineDao;
import com.ssafy.ssafit.model.dto.Routine;

@Service
public class RoutineServiceImpl implements RoutineService{
	private final RoutineDao routineDao;
	
	@Autowired
	public RoutineServiceImpl(RoutineDao routineDao) {
		this.routineDao = routineDao;
	}

	@Override
	public List<Routine> getRoutineList() {
		return routineDao.selectAll();
	}

	@Override
	public void createOne(Routine routine) {
		routineDao.createOne(routine);
	}
	
	
}
