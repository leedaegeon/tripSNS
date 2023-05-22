package com.ssafy.plan.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.service.PlanBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//http://localhost:9999/swagger-ui.html
@RestController
@RequestMapping("/planboard")
@Api("플랜 게시판 컨트롤러 API v1")
public class PlanBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanBoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private PlanBoardService service;
	public PlanBoardController(PlanBoardService service) {
		super();
		this.service = service;
	}
	@GetMapping
	@ApiOperation(value = "플랜 게시판 글목록", notes = "모든 플랜게시판의 게시글의 정보를 반환한다.", response = List.class)
	public List<PlanDto> getPlanBoardList() throws SQLException{
		logger.info("getPlanBoardList - 호출");

		return service.getPlanBoardList();
	}
//	{"userId":"ssafy", "planTitle":"testPlan", "planContent":"test임"}
	@Transactional
	@PostMapping
	@ApiOperation(value="플랜 작성", notes="선택한 여행지 리스트, 글 제목, 글 내용, 유저 정보를 DB에 저장", response=Integer.class)
	public int writePlanBoard(@RequestBody Map<String, Object> map)throws SQLException{
		logger.info("writePlanBoard - 호출");
		logger.info(map.toString());
		int isSuccess = service.writePlanBoard(map);
		logger.info("plan_id있어야함: "+map.toString());
		Map<String, Object> newMap = new HashMap<String, Object>();
		
		String userId = (String)map.get("userId");
		
		service.indexPlanBoardUser(userId);
		
		List<Map<String, Integer>> attrList =  (List<Map<String, Integer>>) map.get("plans");
//			logger.info(attrList);
//			for(Map<String, String> item : attrList) {
//			}
		newMap.put("plan_id", (String)map.get("plan_id"));
		newMap.put("plans", attrList);
		isSuccess = service.writePlans(newMap);
		
		return isSuccess;
	}
	
}

//{"userId":"ssafy",
//	 "planTitle":"testPlan", 
//	 "planContent":"test임", 
//	 "plans":[
//	  {"contentId":"125226"},
//	  {"contentId":"125407"}
//	]}
