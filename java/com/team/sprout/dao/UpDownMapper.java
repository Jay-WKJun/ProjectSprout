package com.team.sprout.dao;

import java.util.List;

import com.team.sprout.vo.UpDown;


public interface UpDownMapper {

	public int updownRegist(UpDown updown);

	public UpDown selectOne(int updown_num);

	public UpDown selectOnes(int aa);

	public int selectUpdownNum();

	
}
