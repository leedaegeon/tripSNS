package com.ssafy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.NoticeBoardDto;

@Mapper
public interface NoticeBoardMapper {

	int writeArticle(NoticeBoardDto boardDto) throws SQLException;

	List<NoticeBoardDto> listArticle() throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	NoticeBoardDto getArticle(int articleNo) throws SQLException;

	void updateHit(int articleNo) throws SQLException;

	int modifyArticle(NoticeBoardDto boardDto) throws SQLException;

	int deleteArticle(int articleNo) throws SQLException;

}