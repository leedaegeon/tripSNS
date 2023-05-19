package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {

	int joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(MemberDto memberDto) throws SQLException;
	
	/* Admin */
	List<MemberDto> listMember() throws SQLException;
	MemberDto getMember(String userId) throws SQLException;
	void updateMember(MemberDto memberDto) throws SQLException;
	void deleteMember(String userId) throws SQLException;
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userId) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	public MemberDto userInfo(String userId) throws SQLException;
}
