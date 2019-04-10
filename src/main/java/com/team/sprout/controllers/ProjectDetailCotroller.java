package com.team.sprout.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team.sprout.dao.MemberRepository;
import com.team.sprout.dao.ProjectMemberRepository;
import com.team.sprout.dao.WorkSpaceRepository;
import com.team.sprout.vo.Member;

@Controller
public class ProjectDetailCotroller {
	@Autowired
	ProjectMemberRepository prrepo;
	@Autowired
	WorkSpaceRepository workrepo;
	@Autowired
	MemberRepository memRepo;	
	List<Member> projectMembers;
	
	final String upLoadPath = "/sproutProjectFileManager";
	//기획... log를 저장해서 이곳에서 통계표를 만든다
	
	//통계표는 log 행동한 데이터의 종류를 숫자로 구분해서 나눈다. 거치는 controller 저장
	//등록 완료 중간저장 6시면 자동저장, 일일 진행사항 = 그 날 업무 할당량 달성정도
	//그 사람이 했다고 제출하면 관리자는 o x를 체크한다.
	//
	
	
	//파일 
	
	/*// 파일 매니저 접속
	@RequestMapping(value = "/fileManager", method = RequestMethod.GET)
	public String whiteBoard(Model model, String postitNumFromProjectNum) {
		if (postitNumFromProjectNum != null) {
			model.addAttribute("projectNum", postitNumFromProjectNum);
		}
		return "project/fileManager";
	}*/
	
	
	
	@RequestMapping(value ="/detailPage", method = RequestMethod.GET)
	public String project_detail(Model model) {

		model.addAttribute("projectNum", "");
		
		return "project/detailPage";
	}

}
