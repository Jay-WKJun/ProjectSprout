package com.team.sprout.dao;

import java.util.Map;

import com.team.sprout.vo.ChatRoom;
import com.team.sprout.vo.Member;

public interface MemberDAO {
	public int memberJoin(Member member);

	public Member selectOne(Map<String, String> map);

	public int updateMember(Member member);

	public Member searchMember(int member_num);

	public Member checkId(String id);
	
	public int setNull_profile(String id);

	public int deleteMember(String id); 
	
	public Member selectOneWebsocket(String userId);

	public int insertcr(ChatRoom cr);

	public void setLoginTime(String member_id); //

	public void userAuth(String userEmail);
	
	public int selectRoomnum();

	public String selectOneMemberNum(String member_name);

	public int insertCertificate(Map<String, String> map); 
}
