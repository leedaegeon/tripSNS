package com.ssafy.board.model.service;

import java.util.List;

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

	public int writeArticle(NoticeBoardDto noticeboardDto) throws Exception {
		return noticeBoardMapper.writeArticle(noticeboardDto);
	}

	public int modifyArticle(NoticeBoardDto noticeboardDto) throws Exception {
		return noticeBoardMapper.modifyArticle(noticeboardDto);
	}

	public int deleteArticle(int articleNo) throws Exception {
		return noticeBoardMapper.deleteArticle(articleNo);
	}

	public void updateHit(int noticeNo) throws Exception {
		noticeBoardMapper.updateHit(noticeNo);
	}
}
