package com.ssafy.plan.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.plan.model.PlanDto;

@Service
public class PlanService {
	
	private PlanService planService;

	public PlanService(PlanService planService) {
		super();
		this.planService = planService;
	}
	public List<PlanDto> selectMine(String userId){
		return planService.selectMine(userId);
	}
}
