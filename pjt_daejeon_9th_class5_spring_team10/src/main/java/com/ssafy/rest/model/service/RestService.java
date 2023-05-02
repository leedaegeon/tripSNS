package com.ssafy.rest.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.rest.model.RestDto;
import com.ssafy.rest.model.mapper.RestMapper;

@Service
public class RestService {
	private RestMapper restMapper;

	public RestService(RestMapper restMapper) {
		super();
		this.restMapper = restMapper;
	}
	
	public List<RestDto> showAll(){
		return restMapper.selectAll();
	}
	
}
