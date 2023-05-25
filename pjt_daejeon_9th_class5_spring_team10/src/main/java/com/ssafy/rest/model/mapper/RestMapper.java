package com.ssafy.rest.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.rest.model.RestDto;

@Mapper
public interface RestMapper {
	//selectAll
	List<RestDto> selectAll();
	//selectOne(restId)
	RestDto selectOne(int restId);

}
