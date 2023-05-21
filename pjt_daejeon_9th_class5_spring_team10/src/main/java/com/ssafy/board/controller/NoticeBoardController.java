package com.ssafy.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.NoticeBoardDto;
import com.ssafy.board.model.service.NoticeBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:9999/swagger-ui.html
@RestController
@RequestMapping("/notice")
@Api("공지 게시판 컨트롤러  API V1")
//@CrossOrigin("*")
public class NoticeBoardController {
	public static final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private NoticeBoardService service;

	public NoticeBoardController(NoticeBoardService service) {
		super();
		this.service = service;
	}

	@GetMapping
	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	public List<NoticeBoardDto> getArticleList() throws Exception {
		return service.listArticle();
	}

	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = NoticeBoardDto.class)
	@GetMapping("/{noticeNo}")
	public ResponseEntity<NoticeBoardDto> getArticle(@PathVariable("noticeNo") int noticeNo) throws Exception {
		service.updateHit(noticeNo);
		return new ResponseEntity<NoticeBoardDto>(service.getArticle(noticeNo), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "게시판에 글 추가", notes = "게시글 작성")
	public ResponseEntity<Map<String, Object>> addArticle(@RequestBody NoticeBoardDto article) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			if (service.writeArticle(article) == 1) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			e.printStackTrace();

			logger.error("글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping
	@ApiOperation(value = "게시판에 글 수정", notes = "게시글 수정")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody NoticeBoardDto article) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			if (service.modifyArticle(article) == 1) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			e.printStackTrace();

			logger.error("글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping("/{noticeNo}")
	@ApiOperation(value = "게시판에 글 삭제", notes = "게시글 삭제")
	public ResponseEntity<Map<String, Object>> removeArticle(@PathVariable("noticeNo") int noticeNo) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			if (service.deleteArticle(noticeNo) == 1) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			e.printStackTrace();

			logger.error("글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
