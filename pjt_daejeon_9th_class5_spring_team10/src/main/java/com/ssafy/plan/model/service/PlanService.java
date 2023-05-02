package com.ssafy.plan.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.mapper.PlanMapper;

@Service
public class PlanService {
	
	private PlanMapper planMapper;

	public PlanService(PlanMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}
//	public List<PlanDto> selectMine(String userId){
//		return planMapper.listPlan(userId);
//	}
}
