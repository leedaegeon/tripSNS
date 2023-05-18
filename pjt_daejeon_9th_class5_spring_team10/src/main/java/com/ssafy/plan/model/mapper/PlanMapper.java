
package com.ssafy.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;
//import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.plan.model.PlanDto;

@Mapper
public interface PlanMapper {
	List<PlanDto> listPlan() throws SQLException;

//	List<PlanDetailDto> listPlanDetail(Map<String, Object> map) throws SQLException;
//
//	PlanDto getPlan(String planId) throws SQLException;
//
//	void updatePlan(PlanDto planDto) throws SQLException;
//
//	void deletePlan(PlanDto planId) throws SQLException;
}
