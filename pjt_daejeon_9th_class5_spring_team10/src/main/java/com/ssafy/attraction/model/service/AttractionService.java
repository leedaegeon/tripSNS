package com.ssafy.attraction.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionService {
	private AttractionMapper attractionMapper;

	public AttractionService(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}

	public List<AttractionDto> listAttractions(AttractionDto param) throws SQLException {
		String keyword = param.getKeyword();
		if (keyword == null) {
			keyword = "";
		}
		param.setKeyword('%'+keyword+'%');
		return attractionMapper.listAttractions(param);
	}

	public AttractionDto getAttraction(int attractionId) throws SQLException {
		return attractionMapper.getAttraction(attractionId);
	}
	
	
}
