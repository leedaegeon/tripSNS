package com.ssafy.plan.controller;

import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.service.PlanBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

}
