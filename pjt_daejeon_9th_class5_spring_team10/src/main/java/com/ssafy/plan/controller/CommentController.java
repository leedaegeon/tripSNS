package com.ssafy.plan.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plan.model.CommentDto;
import com.ssafy.plan.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/planboard/comment")
@Api("플랜 게시판 댓글 컨트롤러 API v1")
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	CommentService commentService;

	@ApiOperation(value = "plan에 해당하는 댓글 목록을 반환", response = List.class)
	@GetMapping("/{planId}")
	public ResponseEntity<List<CommentDto>> listComment(@PathVariable("planId") int planId) {
		logger.debug("listComment - 호출");
		return new ResponseEntity<>(commentService.list(planId), HttpStatus.OK);
	}

	@ApiOperation(value = "댓글 작성", response = String.class)
	@PostMapping
	public ResponseEntity<List<CommentDto>> createComment(@RequestBody CommentDto commentDto) {
		logger.debug("createComment - 호출");
		logger.debug(commentDto.toString());
		List<CommentDto> updatedCommentList = commentService.create(commentDto);
		if(updatedCommentList != null) {
			return new ResponseEntity<>(updatedCommentList, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글 수정", response = String.class)
	@PutMapping
	public ResponseEntity<List<CommentDto>> modifyComment(@RequestBody CommentDto commentDto) {
		logger.debug("modifyComment - 호출");
		logger.debug("" + commentDto);
		List<CommentDto> updatedCommentList = commentService.modify(commentDto);
		if(updatedCommentList != null) {
			return new ResponseEntity<>(updatedCommentList, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글 삭제", response = String.class)
	@DeleteMapping("/{commentNo}")
	public ResponseEntity<List<CommentDto>> deleteComment(@PathVariable("commentNo") int commentNo) {
		logger.debug("deleteComment - 호출");
		List<CommentDto> updatedCommentList = commentService.delete(commentNo);
		if(updatedCommentList != null) {
			return new ResponseEntity<>(updatedCommentList, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
}
