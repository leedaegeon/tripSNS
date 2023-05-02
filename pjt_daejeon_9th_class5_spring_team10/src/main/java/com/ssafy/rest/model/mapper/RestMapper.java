package com.ssafy.rest.model.mapper;

import java.util.List;

import com.ssafy.rest.model.RestDto;

public interface RestMapper {
	//selectAll
	List<RestDto> selectAll();
	//selectOne(restId)
	RestDto selectOne(int restId);

}
