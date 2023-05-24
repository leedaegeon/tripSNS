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
import com.ssafy.attraction.model.mapper.AttractionMapper;
import com.ssafy.plan.controller.PlanBoardController;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.mapper.CommentMapper;
import com.ssafy.plan.model.mapper.PlanMapper;

@Service
public class PlanBoardService {
	private static final Logger logger = LoggerFactory.getLogger(PlanBoardController.class);

	private PlanMapper planMapper;
	private AttractionMapper attractionMapper;
	private CommentMapper commentMapper;
	
	public PlanBoardService(PlanMapper planMapper, AttractionMapper attractionMapper, CommentMapper commentMapper) {
		super();
		this.planMapper = planMapper;
		this.attractionMapper = attractionMapper;
		this.commentMapper = commentMapper;
	}

	public List<PlanDto> getPlanBoardList() throws SQLException {
		List<PlanDto> planBoardList;

		planBoardList = planMapper.listPlan();
		for (int i = 0; i < planBoardList.size(); i++) {
			List<Map<String, Integer>> tempList = getListAttraction(planBoardList.get(i).getPlanId());
			planBoardList.get(i).setPlans(tempList);
		}

		return planBoardList;
	}

	public List<Map<String, Integer>> getListAttraction(int planId) throws SQLException {
		List<Map<String, Integer>> AttrList = planMapper.listAttraction(planId);
		return AttrList;

	}

	public List<PlanDto> getMyPlan(String userId) throws SQLException {
		List<PlanDto> planBoardList;

		planBoardList = planMapper.listMyPlanBoard(userId);
		for (int i = 0; i < planBoardList.size(); i++) {
			List<Map<String, Integer>> tempList = getListAttraction(planBoardList.get(i).getPlanId());
			planBoardList.get(i).setPlans(tempList);
		}

		return planBoardList;
	}

	public List<PlanDto> getWishPlan(String userId) throws SQLException {
		List<PlanDto> planBoardList;

		planBoardList = planMapper.listWishPlanBoard(userId);
		for (int i = 0; i < planBoardList.size(); i++) {
			List<Map<String, Integer>> tempList = getListAttraction(planBoardList.get(i).getPlanId());
			planBoardList.get(i).setPlans(tempList);
		}

		return planBoardList;
	}

	public List<PlanDto> getCommentedPlan(String userId) throws SQLException {
		List<PlanDto> planBoardList;

		planBoardList = planMapper.listCommentedPlanBoard(userId);
		for (int i = 0; i < planBoardList.size(); i++) {
			List<Map<String, Integer>> tempList = getListAttraction(planBoardList.get(i).getPlanId());
			planBoardList.get(i).setPlans(tempList);
		}

		return planBoardList;
	}

	public List<PlanDto> getPlanDetailList(List<PlanDto>data) throws SQLException{
		for(int i=0; i<data.size(); i++) {
			List<Map<String, Integer>> plans = data.get(i).getPlans();
			List<AttractionDto> attrList = new ArrayList<AttractionDto>();
			for(int j=0; j<plans.size(); j++) {
				attrList.add(attractionMapper.getAttraction(plans.get(j).get("ATTR_ID")));
			}
			data.get(i).setAttrInfos(attrList);
			
			data.get(i).setComments(commentMapper.list(data.get(i).getPlanId()));
		}
		
		
		return data;
		
	}
	
	public List<PlanDto> getPlanDetailListWithUserId(List<PlanDto> data, String userId) throws SQLException {
		List<Integer> likedPList = planMapper.likedPlanIdList(userId);
		for (int i = 0; i < data.size(); i++) {
			List<Map<String, Integer>> plans = data.get(i).getPlans();
			List<AttractionDto> attrList = new ArrayList<AttractionDto>();
			for (int j = 0; j < plans.size(); j++) {
				attrList.add(attractionMapper.getAttraction(plans.get(j).get("ATTR_ID")));
			}
			data.get(i).setAttrInfos(attrList);
			data.get(i).setComments(commentMapper.list(data.get(i).getPlanId()));

			// 찜하기 목록에 있는 플랜인지
			if (likedPList.contains(data.get(i).getPlanId())) {
				data.get(i).setWish(true);
			}
		}
		
		return data;

	}


	public int writePlanBoard(Map<String, Object> map) throws SQLException {
		int isSuccess = planMapper.writePlanBoard(map);
		return isSuccess;
	}

	public int indexPlanBoardUser(String userId) throws SQLException {
		int isSuccess = planMapper.indexPlanBoardUser(userId);
		return isSuccess;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public int writePlans(Map<String, Object> map) throws SQLException {
		int isSuccess = 0;

		Map<String, String> newMap = new HashMap<String, String>();
		String planId = (String) map.get("plan_id");
		int order = 1;
		newMap.put("plan_id", planId);
		List<Map<String, Integer>> attrList = (List<Map<String, Integer>>) map.get("plans");
		for (Map<String, Integer> item : attrList) {

			newMap.put("contentId", Integer.toString(item.get("contentId")));
			newMap.put("pOrder", Integer.toString(order));
			order++;
//			logger.info(newMap.toString());
			isSuccess = planMapper.writePlans(newMap);

		}
		return isSuccess;
	}
}
