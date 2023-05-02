package com.ssafy.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.attraction.model.AttractionDto;

public interface AttractionMapper {
	List<AttractionDto> listAttraction(Map<String, Object> param) throws SQLException;

	int getTotalAttractionCount(Map<String, Object> param) throws SQLException;

	AttractionDto getAttraction(int contentId) throws SQLException;

}
