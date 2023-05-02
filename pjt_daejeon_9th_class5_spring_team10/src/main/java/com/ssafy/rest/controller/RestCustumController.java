package com.ssafy.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.rest.model.RestDto;
import com.ssafy.rest.model.service.RestService;

@RestController
@RequestMapping("/rest")
public class RestCustumController {

	
	private RestService restService;

	public RestCustumController(RestService restService) {
		super();
		this.restService = restService;
	}
	@GetMapping
	public List<RestDto> restList() {
	
		List<RestDto> list = restService.showAll();
		
		return list;

	}
	
}
