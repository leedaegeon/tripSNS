package com.ssafy.plan.model.mapper;

import java.util.List;

import com.ssafy.plan.model.CommentDto;

public interface CommentMapper {

	List<CommentDto> list(int planId);

	int create(CommentDto commentDto);

	int modify(CommentDto commentDto);

	int delete(int commentNo);

}
