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

	public List<AttractionDto> listAttractions(Map<String, String> param) throws SQLException {		
		String keyword = param.get("keyword");
		if (keyword == null) {
			keyword = "";
		}
		param.put("keyword", '%'+keyword+'%');
		int nowPage = Integer.parseInt(param.get("nowPage"));
		int max = (nowPage+8);
		param.put("max", Integer.toString(max));
		return attractionMapper.listAttractions(param);
	}

	public AttractionDto getAttraction(int attractionId) throws SQLException {
		return attractionMapper.getAttraction(attractionId);
	}
	public String getAttractionDetail(String contentId) throws SQLException {
		
		return attractionMapper.getAttractionDescription(contentId); 
	}
	
}
