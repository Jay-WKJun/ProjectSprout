package com.team.sprout.dao;

import java.util.Map;

import com.team.sprout.vo.Member;

public interface MemberDAO {
	public int memberJoin(Member member);

	public Member selectOne(Map<String, String> map);

	public int updateMember(Member member);
}
