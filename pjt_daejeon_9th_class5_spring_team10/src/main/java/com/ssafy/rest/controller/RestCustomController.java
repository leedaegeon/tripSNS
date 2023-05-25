package com.ssafy.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.rest.model.RestDto;
import com.ssafy.rest.model.service.RestService;
//http://localhost:9999/swagger-ui.html

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest")
@Api("휴게소 게시판 컨트롤러 API V1")
public class RestCustomController {

	private RestService restService;

	public RestCustomController(RestService restService) {
		super();
		this.restService = restService;
	}

	@GetMapping(value = "/list")
	@ApiOperation(value="휴게소 리스트", notes="모든 휴게소 정보를 반환한다", response=List.class)
	public List<RestDto> restList() {

		List<RestDto> list = restService.showAll();

		return list;

	}


	@GetMapping(value = "/one/{restId}")
	@ApiOperation(value="휴게소 검색", notes="휴게소 아이디에 해당하는 휴게소 정보를 반환한다", response=RestDto.class)
	public RestDto getRest(@PathVariable int restId) {
		RestDto rest = restService.showOne(restId);
		return rest;
	}

}
