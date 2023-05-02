package com.ssafy.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
public class MemberController {
	Logger logger = LoggerFactory.getLogger(MemberController.class);

	private MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}
	
	@PostMapping("/join")
	public int join(MemberDto memberDto, Model model) {
		try {
			memberService.joinMember(memberDto);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return -1;
		}
	}
	
	
	@PostMapping("/login")
	public int login(@RequestParam Map<String, String> map, @RequestParam(name = "saveid", required = false) String saveid, Model model, HttpSession session, HttpServletResponse response) {
	
		try {
			MemberDto memberDto = memberService.loginMember(map);
			if(memberDto != null) {
				session.setAttribute("userinfo", memberDto);
				
				Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
				cookie.setPath("/board");
				if("ok".equals(saveid)) {
					cookie.setMaxAge(60*60*24*365*40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				return 1;
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return -1;
		}
	}
	
	@GetMapping("/logout")
	public int logout(HttpSession session) {
		session.invalidate();
		return 1;
	}
	
	@GetMapping("/list")
	public List<MemberDto> list() throws Exception {
		return memberService.listMember();
		
	}
	@PutMapping("/modify")
	public int modify(@RequestBody MemberDto memberDto) {
		try {
			memberService.updateMember(memberDto);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("예외발생");
			return -1;
		}
		
	}
	@DeleteMapping(value = "delete/{userid}")
	public int delete(@PathVariable("userid") String userId) {
		try {
			memberService.deleteMember(userId);
			return 1;
		}catch (Exception e) {
			logger.debug("예외발생");
			return -1;
		}
	}
}
