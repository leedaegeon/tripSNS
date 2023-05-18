package com.ssafy.plan.model.service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.mapper.PlanMapper;

@Service
public class PlanBoardService {

	private PlanMapper planMapper;

	public PlanBoardService(PlanMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}
	public List<PlanDto> getPlanBoardList() throws SQLException {
		List<PlanDto> planboardList;
		
		planboardList = planMapper.listPlan();
		return planboardList;
	}
	
}
