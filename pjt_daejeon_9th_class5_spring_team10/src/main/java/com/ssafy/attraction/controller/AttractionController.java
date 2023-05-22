package com.ssafy.attraction.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.service.AttractionService;
import com.ssafy.plan.controller.PlanBoardController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//http://localhost:9999/swagger-ui.html
import io.swagger.annotations.ApiParam;
//http://localhost:9999/swagger-ui.html

@RestController
@RequestMapping("/attraction")
@CrossOrigin("*")
@Api("여행 컨트롤러  API V1")
public class AttractionController {
	private static final Logger logger = LoggerFactory.getLogger(PlanBoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private AttractionService service;

	public AttractionController(AttractionService service) {
		super();
		this.service = service;
	}
	
	@GetMapping()
	@ApiOperation(value="여행지 리스트 검색", notes="검색한 여행지 정보를 리스트로 반환", response=List.class)
	public List<AttractionDto> searchAttractions(@RequestParam @ApiParam(value="여행지 리스트를 받아올 때 필요한 검색어", required=true) Map<String, String> param) throws Exception {
//		System.out.println(param.get("contentTypeId") );
//		System.out.println(param.get("sidoCode"));
//		System.out.println(param.get("gugunCode"));
//		System.out.println(param.get("keyword"));
		
		return service.listAttractions(param);
	}
	
	@GetMapping("/specific")
	@ApiOperation(value="특정 contentId에 해당하는 여행지 리스트 반환",notes="contentId 배열이 들어올 때 해당하는 여행지 리스트를 반환" , response=List.class)
	public List<AttractionDto> getAttractions(@RequestParam int[] contentIds) throws Exception {
//		for(int i=0; i<contentIds.length; i++) {
//			logger.info(Integer.toString(contentIds[i]));
//		}
		return service.getAttraction(contentIds);
	}
	
	@GetMapping("/{contentId}")
	@ApiOperation(value="여행지 설명 얻어오기", notes="선택한 여행지의 설명을 얻어오는 함수", response=String.class)
	public String getAttractionDetail(@PathVariable("contentId") String contentId) throws Exception{
		
		return service.getAttractionDetail(contentId);
	}
	
}
