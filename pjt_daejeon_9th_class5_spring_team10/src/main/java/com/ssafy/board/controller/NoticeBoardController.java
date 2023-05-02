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

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeBoardController {

	private NoticeBoardService service;

	public NoticeBoardController(NoticeBoardService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<NoticeBoardDto> getArticleList() throws Exception {
		return service.listArticle();
	}
	
	@GetMapping("/{noticeNo}")
	public NoticeBoardDto getArticle(@PathVariable("noticeNo") int noticeNo) throws Exception {
		return service.getArticle(noticeNo);
	}
	
	@PostMapping
	public void addBook(@RequestBody NoticeBoardDto article) throws Exception {
		service.writeArticle(article);
	}
	
	@PutMapping
	public void modify(@RequestBody NoticeBoardDto article) throws Exception {
		service.modifyArticle(article);
	}
	
	@DeleteMapping("/{noticeNo}")
	public void removeBook(@PathVariable("noticeNo") int noticeNo) throws Exception {
		service.deleteArticle(noticeNo);
	}
}
