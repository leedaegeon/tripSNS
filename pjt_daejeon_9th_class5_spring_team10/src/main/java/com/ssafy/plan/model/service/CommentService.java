package com.ssafy.plan.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.plan.model.CommentDto;
import com.ssafy.plan.model.mapper.CommentMapper;

@Service
public class CommentService {
	private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

	@Autowired
	private CommentMapper commentMapper;

	public CommentService(CommentMapper commentMapper) {
		super();
		this.commentMapper = commentMapper;
	}

	public List<CommentDto> list(int planId) {
		return commentMapper.list(planId);
	}

	public boolean create(CommentDto commentDto) {
		return commentMapper.create(commentDto) == 1;
	}

	public boolean modify(CommentDto commentDto) {
		return commentMapper.modify(commentDto) == 1;
	}

	public boolean delete(int commentNo) {
		return commentMapper.delete(commentNo) == 1;
	}

}
