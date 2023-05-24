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

	public List<CommentDto> create(CommentDto commentDto) {
		if (commentMapper.create(commentDto) == 1) {
			return commentMapper.list(Integer.parseInt(commentDto.getPlanId()));
		}
		return null;
	}

	public List<CommentDto> modify(CommentDto commentDto) {
		int planId = commentMapper.getPlanId(commentDto.getCommentNo());
		if (commentMapper.modify(commentDto) == 1) {
			return commentMapper.list(planId);
		}
		return null;
	}

	public List<CommentDto> delete(int commentNo) {
		int planId = commentMapper.getPlanId(commentNo);
		if (commentMapper.delete(commentNo) == 1) {
			return commentMapper.list(planId);
		}
		return null;
	}
}
