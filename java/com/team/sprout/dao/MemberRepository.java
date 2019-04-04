package com.team.sprout.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.ChatRoom;
import com.team.sprout.vo.Member;


@Repository
public class MemberRepository {

	@Autowired
	SqlSession session;
	
	public int memberJoin(Member member) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		int result = dao.memberJoin(member);
		
		return result;
	}

	public Member selectOne(String userId) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		Member member = null;
		
		member = dao.selectOne(userId);
		
		return member;
	}


	public int updateMember(Member member) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		int result = dao.updateMember(member);
		
		return result;
	}

	public Member searchMember(int member_num) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		Member member=dao.searchMember(member_num);
		return member;
	}

	public Member checkId(String id) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		Member result = dao.checkId(id);
		return result; 
		
	}

	
	
	public int insertRoomnum(ChatRoom cr) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		int result = dao.insertcr(cr);
		return result;
	}

}
