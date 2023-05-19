package com.ssafy.map.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.map.model.SidoGugunCodeDto;
import com.ssafy.map.model.mapper.SidoGugunCodeMapper;



@Service
public class SidoGugunCodeService {
	
	@Autowired
	private SidoGugunCodeMapper mapper;
	
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return mapper.getSido();
	}

	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return mapper.getGugunInSido(sido);
	}




}
