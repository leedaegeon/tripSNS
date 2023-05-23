package com.ssafy.plan.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plan.model.WishListDto;
import com.ssafy.plan.model.service.WishListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
//http://localhost:9999/swagger-ui.html
@RestController
@RequestMapping("/planboard/wishlist")
@Api("플랜 게시판 찜하기 컨트롤러 API v1")
public class WishListController {
	
	private static final Logger logger = LoggerFactory.getLogger(WishListController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private WishListService service;
	public WishListController(WishListService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	@ApiOperation(value="찜하기", notes="찜하기 목록에 플랜아이디, 사용자 아이디 추가", response=Integer.class)
	public ResponseEntity<Map<String, Object>> addWishList(@RequestBody @ApiParam(value = "찜하기 목록에 추가할 플랜 아이디와 사용자 아이디", required = true) WishListDto wishDto){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			if (service.insert(wishDto) == 1) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			e.printStackTrace();

			logger.error("찜하기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/delete")
	@ApiOperation(value="찜하기 취소", notes="찜하기 목록에서 플랜아이디, 사용자 아이디 제거", response=Integer.class)
	public ResponseEntity<Map<String, Object>> removeWishList(@RequestBody @ApiParam(value = "찜하기 목록에서 제거할 플랜 아이디와 사용자 아이디", required = true) WishListDto wishDto){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			System.out.println("삭제할 DTO: "+wishDto);
			if (service.delete(wishDto) == 1) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			e.printStackTrace();

			logger.error("찜하기 취소 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
