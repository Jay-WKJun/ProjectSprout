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

	public Member selectOne(Map<String, String> map) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		Member m = dao.selectOne(map);
		System.out.println("member : "+ m.toString());
		return m;
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
	
	// DB에서 프로필 사진 삭제하기.    <환>
	public int setNull_profile(String id) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		int result = dao.setNull_profile(id);
		return result;
	} // <환>
	
	
	//websocket에 필요한거
	public Member selectOneWebsocket(String userId) {
		Member member = null;
		try {
			
			MemberDAO dao = session.getMapper(MemberDAO.class);
			System.out.println("유저아이디"+userId);
			
			member = dao.selectOneWebsocket(userId);
			System.out.println("member : "+ member.toString());
			return member;
		} catch (Exception e) {
			// TODO: handle exception
				
			return member;
		}
		
	}

	public int insertRoomnum(ChatRoom cr) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		int result = dao.insertcr(cr);
		return result;
	}

	public void deleteMember(String id) {
		MemberDAO dao = session.getMapper(MemberDAO.class);
		int result = dao.deleteMember(id);		
	}

	public int selectRoomnum() {

		MemberDAO dao = session.getMapper(MemberDAO.class);
		int results = dao.selectRoomnum();
		return results;
	}

	public String selectOneMemberNum(String member_name) {
		// TODO Auto-generated method stub
		
		MemberDAO dao = session.getMapper(MemberDAO.class);
		String member_nameOne = dao.selectOneMemberNum(member_name);
		
		return member_nameOne;
	}


}
