package com.team.sprout.dao;

import java.util.Map;

import com.team.sprout.vo.ChatRoom;
import com.team.sprout.vo.Member;

public interface MemberDAO {
	public int memberJoin(Member member);

	public Member selectOne(String userId);

	public int updateMember(Member member);

	public Member searchMember(int member_num);

	public Member checkId(String id);

	public int insertcr(ChatRoom cr);


}
