
package com.ssafy.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.plan.model.PlanDto;

@Mapper
public interface PlanMapper {
	List<PlanDto> listPlan() throws SQLException;

	List<Map<String, Integer>> listAttraction(int planId) throws SQLException;

	List<PlanDto> listMyPlanBoard(String userId) throws SQLException;

	int writePlanBoard(Map<String, Object> map) throws SQLException;

	int indexPlanBoardUser(String userId) throws SQLException;

	int writePlans(Map<String, String> map) throws SQLException;
	
	List<PlanDto> listCommentedPlanBoard(String userId) throws SQLException;
	
	List<PlanDto> listWishPlanBoard(String userId) throws SQLException;
	
	List<Integer> likedPlanIdList(String userId) throws SQLException;

//	List<PlanDetailDto> listPlanDetail(Map<String, Object> map) throws SQLException;
//
	PlanDto getPlan(int planId) throws SQLException;

	int updatePlan(PlanDto planDto) throws SQLException;
	
	int deletePlanDetail(int planId) throws SQLException;

	int deletePlan(int planId) throws SQLException;
}
