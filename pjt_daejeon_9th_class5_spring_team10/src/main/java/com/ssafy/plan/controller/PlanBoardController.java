package com.ssafy.plan.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plan.model.CommentDto;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.service.CommentService;
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

	public PlanBoardController(PlanBoardService service, CommentService commentService) {
		super();
		this.service = service;
	}

	@GetMapping
	@ApiOperation(value = "플랜 게시판 글목록", notes = "모든 플랜게시판의 게시글의 정보를 반환한다.", response = List.class)
	public List<PlanDto> getPlanBoardList() throws SQLException {
		logger.info("getPlanBoardList - 호출");
		return service.getPlanBoardList();
	}

	@GetMapping("/myplan")
	@ApiOperation(value = "내가 쓴 플랜게시판의 글 목록", notes = "플랜게시판에 작성한 내 글 목록을 반환한다", response = List.class)
	public List<PlanDto> getMyPlanBoardList(@RequestParam String userId) throws SQLException {
		logger.info("getMyPlanBoardList - 호출");
		List<PlanDto> data = service.getMyPlan(userId);
		return service.getPlanDetailListWithUserId(data, userId);
	}

	@GetMapping("/wish")
	@ApiOperation(value = "내가 쓴 플랜게시판의 글 목록", notes = "플랜게시판에 작성한 내 글 목록을 반환한다", response = List.class)
	public List<PlanDto> getWishPlanBoardList(@RequestParam String userId) throws SQLException {
		logger.info("getWishPlanBoardList - 호출");
		List<PlanDto> data = service.getWishPlan(userId);
		return service.getPlanDetailListWithUserId(data, userId);
	}

	@GetMapping("/commented")
	@ApiOperation(value = "내가 쓴 플랜게시판의 글 목록", notes = "플랜게시판에 작성한 내 글 목록을 반환한다", response = List.class)
	public List<PlanDto> getCommentedPlanBoardList(@RequestParam String userId) throws SQLException {
		logger.info("getCommentedPlanBoardList - 호출");

		List<PlanDto> data = service.getCommentedPlan(userId);
		return service.getPlanDetailListWithUserId(data, userId);
	}

	@GetMapping("/detail")
	@ApiOperation(value = "모든 사람이 작성한 플랜 게시판의 글 목록 및 여행지", notes = "모든 사람이 작성한 플랜 게시판의 글 목록 및 여행지", response = List.class)
	public List<PlanDto> getPlanDetailList() throws SQLException {
		
		logger.info("getPlanDetailList");
		List<PlanDto> data = service.getPlanBoardList();

		return service.getPlanDetailList(data);
	}

	@GetMapping("/detail/{planId}")
	@ApiOperation(value = "플랜  상세 정보", notes = "플랜 글 및 여행지 목록을 반환한다", response = PlanDto.class)
	public PlanDto getPlan(@PathVariable("planId") int planId) throws SQLException {
		logger.info("getPlan");
		PlanDto data = service.getPlan(planId);

		return service.getPlanDetail(data);
	}

	/////////////////////////////////////////
	@GetMapping("/detail-with-userid")
	@ApiOperation(value = "모든 사람이 작성한 플랜 게시판의 글 목록 및 여행지 + 찜하기 목록에 있는지", notes = "모든 사람이 작성한 플랜 게시판의 글 목록 및 여행지", response = List.class)
	public List<PlanDto> getPlanDetailListWithUserId(@RequestParam String userId) throws SQLException {
		logger.info("getPlanDetailListWithUserId");
		List<PlanDto> data = service.getPlanBoardList();

		return service.getPlanDetailListWithUserId(data, userId);
	}

	/////////////////////////////////////////

//	{"userId":"ssafy", "planTitle":"testPlan", "planContent":"test임"}
	@Transactional
	@PostMapping
	@ApiOperation(value = "플랜 작성", notes = "선택한 여행지 리스트, 글 제목, 글 내용, 유저 정보를 DB에 저장", response = Integer.class)
	public int writePlanBoard(@RequestBody Map<String, Object> map) throws SQLException {
		logger.info("writePlanBoard - 호출");
		logger.info(map.toString());
		
		
		int isSuccess = service.writePlanBoard(map);
		logger.info("plan_id있어야함: " + map.toString());
//		Map<String, Object> newMap = new HashMap<String, Object>();

		String userId = (String) map.get("userId");

		service.indexPlanBoardUser(userId);

//		List<Map<String, Integer>> attrList = (List<Map<String, Integer>>) map.get("plans");
//
//		newMap.put("plan_id", (String) map.get("plan_id"));
//		newMap.put("plans", attrList);
//		isSuccess = service.writePlans(newMap);
		isSuccess = service.writePlans(map);

		return isSuccess;
	}

	@ApiOperation(value = "플랜 삭제", response = Integer.class)
	@DeleteMapping("/{planId}")
	public ResponseEntity<Map<String, Object>> removePlan(@PathVariable("planId") int planId) {
		logger.debug("removePlan - 호출");
		logger.debug(Integer.toString(planId));
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			if (service.deletePlan(planId) == 1) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error("플랜 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "플랜 수정", response = Integer.class)
	@PutMapping
	public ResponseEntity<Map<String, Object>> modifyPlan(@RequestBody PlanDto planDto) {
		logger.debug("modifyPlan - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			System.out.println("수정할 planDTO: " + planDto);
			System.out.println(planDto.getAttrInfos());
			int isSuccess = service.updatePlan(planDto);
			if (isSuccess != 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} 
		} catch (Exception e) {
			e.printStackTrace();

			logger.error("플랜 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}

//{"userId":"ssafy",
//	 "planTitle":"testPlan", 
//	 "planContent":"test임", 
//	 "plans":[
//	  {"contentId":"125226"},
//	  {"contentId":"125407"}
//	]}
