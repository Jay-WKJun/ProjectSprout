package com.team.sprout.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	public Member selectOne(Map<String, String> map) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		Member m = dao.selectOne(map);
		
		return m;
	}

	public int updateMember(Member member) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		int result = dao.updateMember(member);
		
		return result;
	}
	
	
	
}
