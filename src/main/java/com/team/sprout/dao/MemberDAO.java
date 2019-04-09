package com.team.sprout.dao;

import java.util.Map;

import com.team.sprout.vo.Member;

public interface MemberDAO {
	public int memberJoin(Member member);

	public Member selectOne(Map<String, String> map);

	public int updateMember(Member member);

	public Member searchMember(int member_num);

	public Member checkId(String id);
	
	public int setNull_profile(String id);

	public int deleteMember(String id); 
}
