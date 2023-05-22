package com.ssafy.plan.model.service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.plan.controller.PlanBoardController;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.mapper.PlanMapper;

@Service
public class PlanBoardService {
	private static final Logger logger = LoggerFactory.getLogger(PlanBoardController.class);

	private PlanMapper planMapper;

	public PlanBoardService(PlanMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}
	
	public List<PlanDto> getPlanBoardList() throws SQLException {
		List<PlanDto> planBoardList;
		
		planBoardList = planMapper.listPlan();
		for(int i=0; i<planBoardList.size(); i++) {
			List<Map<String, Integer>> tempList = getListAttraction(planBoardList.get(i).getPlanId());
			planBoardList.get(i).setPlans(tempList);
		}
		
		return planBoardList;
	}
	public List<Map<String, Integer>> getListAttraction(int planId)throws SQLException{
		List<Map<String, Integer>> AttrList = planMapper.listAttraction(planId);
		return AttrList;
		
	}
	public List<PlanDto> getMyPlan(String userId) throws SQLException{
		List<PlanDto> planBoardList;
		
		planBoardList = planMapper.listMyPlanBoard(userId);
		for(int i=0; i<planBoardList.size(); i++) {
			List<Map<String, Integer>> tempList = getListAttraction(planBoardList.get(i).getPlanId());
			planBoardList.get(i).setPlans(tempList);
		}
		
		return planBoardList;
	}
	
	public List<PlanDto> getWishPlan(String userId) throws SQLException{
		List<PlanDto> planBoardList;
		
		planBoardList = planMapper.listWishPlanBoard(userId);
		for(int i=0; i<planBoardList.size(); i++) {
			List<Map<String, Integer>> tempList = getListAttraction(planBoardList.get(i).getPlanId());
			planBoardList.get(i).setPlans(tempList);
		}
		
		return planBoardList;
	}
	public int writePlanBoard(Map<String, Object> map)throws SQLException{
		int isSuccess = planMapper.writePlanBoard(map);
		return isSuccess;
	}
	
	public int indexPlanBoardUser(String userId)throws SQLException{
		int isSuccess = planMapper.indexPlanBoardUser(userId);
		return isSuccess;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public int writePlans(Map<String, Object> map)throws SQLException{
		int isSuccess = 0;
		
		Map<String, String> newMap = new HashMap<String, String>();
		String planId = (String) map.get("plan_id");
		int order = 1;
		newMap.put("plan_id", planId);
		List<Map<String, Integer>> attrList = (List<Map<String, Integer>>) map.get("plans");
		for(Map<String, Integer> item : attrList) {
			
			newMap.put("contentId", Integer.toString(item.get("contentId")));
			newMap.put("pOrder", Integer.toString(order));
			order++;
//			logger.info(newMap.toString());
			isSuccess = planMapper.writePlans(newMap);
			
		}
		return isSuccess;
	}
}
