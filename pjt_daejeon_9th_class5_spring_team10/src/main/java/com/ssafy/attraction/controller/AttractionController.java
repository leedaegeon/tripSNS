package com.ssafy.attraction.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.service.AttractionService;

@RestController
@RequestMapping("/attraction")
@CrossOrigin("*")
public class AttractionController {
	private AttractionService service;

	public AttractionController(AttractionService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<AttractionDto> searchAttractions(Map<String, Object> param) throws Exception {
		return service.listAttractions(param);
	}
	
	@GetMapping("/{attractionId}")
	public AttractionDto getArticle(@PathVariable("attractionId") int attractionId) throws Exception {
		return service.getAttraction(attractionId);
	}
	
}
