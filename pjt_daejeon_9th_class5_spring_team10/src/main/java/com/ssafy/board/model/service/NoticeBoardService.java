package com.ssafy.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.board.model.NoticeBoardDto;
import com.ssafy.board.model.mapper.NoticeBoardMapper;

@Service
public class NoticeBoardService {

	private NoticeBoardMapper noticeBoardMapper;

	public NoticeBoardService(NoticeBoardMapper noticeBoardMapper) {
		super();
		this.noticeBoardMapper = noticeBoardMapper;
	}
	
	public List<NoticeBoardDto> listArticle() throws Exception {
		return noticeBoardMapper.listArticle();
	}
	
	public NoticeBoardDto getArticle(int articleNo) throws Exception {
		return noticeBoardMapper.getArticle(articleNo);
	}
}
