package com.ssafy.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:9999/swagger-ui.html

@RestController
@RequestMapping("/user")
@Api("멤버 컨트롤러 API V1")
public class MemberController {
	Logger logger = LoggerFactory.getLogger(MemberController.class);

	private MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}
	
	@PostMapping("/join")
	@ApiOperation(value="회원가입", notes="회원정보를 DB에 저장", response=MemberDto.class)
	public MemberDto join(MemberDto memberDto, Model model) {
		try {
			memberService.joinMember(memberDto);
			
			return memberDto;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return null;
		}
	}
	
	
	@PostMapping("/login")
	@ApiOperation(value="로그인", notes="아이디와 비밀번호를 입력하여 로그인", response=MemberDto.class)
	public MemberDto login(@RequestParam Map<String, String> map, @RequestParam(name = "saveid", required = false) String saveid, Model model, HttpSession session, HttpServletResponse response) {
	
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
				return memberDto;
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return memberDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return null;
		}
	}
	
	@GetMapping("/logout")
	@ApiOperation(value="로그아웃", notes="로그아웃", response=MemberDto.class)
	public MemberDto logout(HttpSession session) {
		MemberDto userInfo = (MemberDto)session.getAttribute("userinfo");
		session.invalidate();
		return userInfo;
	}
	
	@GetMapping("/list")
	@ApiOperation(value="회원리스트", notes="회원 리스트를 반환", response=List.class)
	public List<MemberDto> list() throws Exception {
		return memberService.listMember();
		
	}
	@PutMapping("/modify")
	@ApiOperation(value="회원 정보 수정", notes="회원 정보 수정", response=MemberDto.class)
	public MemberDto modify(@RequestBody MemberDto memberDto) {
		try {
			memberService.updateMember(memberDto);
			return memberDto;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("예외발생");
			return memberDto;
		}
		
	}
	@DeleteMapping(value = "delete/{userid}")
	@ApiOperation(value="회원 탈퇴", notes="회원 정보 삭제", response=MemberDto.class)
	public MemberDto delete(@PathVariable("userid") String userId) {
		try {
			MemberDto deletedMember = memberService.getMember(userId);
			logger.debug(deletedMember.toString());
			memberService.deleteMember(userId);
			return deletedMember;
		}catch (Exception e) {
			logger.debug("예외발생");
			return null;
		}
	}
}
