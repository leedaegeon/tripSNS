package com.ssafy.member.model.service;

import java.util.HashMap;
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
	
	public int joinMember(MemberDto memberDto) throws Exception {
		return memberMapper.joinMember(memberDto);
	}
	public MemberDto loginMember(MemberDto memberDto) throws Exception {
		if (memberDto.getUserId() == null || memberDto.getUserPw() == null)
			return null;
		return memberMapper.loginMember(memberDto);
	}
	
	/* JWT */

//	public MemberDto userInfo(String userid) throws Exception {
//		return memberMapper.userInfo(userid);
//	}

	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userid);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	public Object getRefreshToken(String userid) throws Exception {
		return memberMapper.getRefreshToken(userid);
	}

	public void deleteRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userid);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
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

	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}
	
}
