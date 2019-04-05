package com.team.sprout.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.UpDown;


@Repository
public class UpDownDAO {

	@Autowired
	private SqlSession sqlsession;

	public int updownRegist(UpDown updown) {

		UpDownMapper mapper = sqlsession.getMapper(UpDownMapper.class);
		int updowns = mapper.updownRegist(updown);
		return updowns;
	}

	public UpDown seletOne(int updown_num) {
		UpDownMapper mapper = sqlsession.getMapper(UpDownMapper.class);
		UpDown updown = mapper.selectOne(updown_num);
		
		return updown;
	}

	public int selectUpdownNum() {
		UpDownMapper mapper = sqlsession.getMapper(UpDownMapper.class);
		return mapper.selectUpdownNum() ;
	}

	
}
