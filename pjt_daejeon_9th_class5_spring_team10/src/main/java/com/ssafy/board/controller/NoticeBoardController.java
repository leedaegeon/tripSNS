package com.ssafy.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
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
	public NoticeBoardDto getArticle(@PathVariable("noticeNo") int noticeNo) throws Exception {
		return service.getArticle(noticeNo);
	}
	
	@PostMapping
	@ApiOperation(value="게시판에 글 추가", notes="게시글 작성")
	public void addBook(@RequestBody NoticeBoardDto article) throws Exception {
		service.writeArticle(article);
	}
	
	@PutMapping
	@ApiOperation(value="게시판에 글 수정", notes="게시글 수정")
	public void modify(@RequestBody NoticeBoardDto article) throws Exception {
		service.modifyArticle(article);
	}
	
	
	@DeleteMapping("/{noticeNo}")
	@ApiOperation(value="게시판에 글 삭제", notes="게시글 삭제")
	public void removeBook(@PathVariable("noticeNo") int noticeNo) throws Exception {
		service.deleteArticle(noticeNo);
	}
}
