package com.ssafy.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class MemberService {
	MemberMapper memberMapper;

	public MemberService(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	public void joinMember(MemberDto memberDto) throws Exception {
		memberMapper.joinMember(memberDto);
	}
	public MemberDto loginMember(Map<String, String> map) throws Exception {
		return memberMapper.loginMember(map);
	}
	
	/* ADMIN */
	
	public List<MemberDto> listMember() throws Exception {
		return memberMapper.listMember();
	}

	
	public MemberDto getMember(String userId) throws Exception {
		return memberMapper.getMember(userId);
	}

	public void updateMember(MemberDto memberDto) throws Exception {
		memberMapper.updateMember(memberDto);
	}

	public void deleteMember(String userId) throws Exception {
		memberMapper.deleteMember(userId);		
	}
	
}
