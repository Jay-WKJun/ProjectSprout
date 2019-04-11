package com.team.sprout.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team.sprout.dao.MemberRepository;
import com.team.sprout.dao.ProjectMemberRepository;
import com.team.sprout.dao.WorkSpaceRepository;
import com.team.sprout.util.DocumentFileService;
import com.team.sprout.vo.DocumentBoard;
import com.team.sprout.vo.DocumentFolder;
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
	
	final String upLoadPath = "/sproutDocumentFileManager";
	//기획... log를 저장해서 이곳에서 통계표를 만든다
	
	//통계표는 log 행동한 데이터의 종류를 숫자로 구분해서 나눈다. 거치는 controller 저장
	//등록 완료 중간저장 6시면 자동저장, 일일 진행사항 = 그 날 업무 할당량 달성정도
	//그 사람이 했다고 제출하면 관리자는 o x를 체크한다.
	//
	
	
	//파일 
	
	/*// 파일 매니저 접속
	@RequestMapping(value = "/documentManager", method = RequestMethod.GET)
	public String whiteBoard(Model model, String postitNumFromProjectNum) {
		//접속시 필요한 것
		//mainproject_projectnum으로 모든 폴더 찾기
		//각 폴더가 가진 파일들을 select형태로 풀어서 표현한다.
		//select의 첫 이름은 폴더의 이름이다.
		
		
		
		if (postitNumFromProjectNum != null) {
			model.addAttribute("projectNum", postitNumFromProjectNum);
		}
		return "project/fileManager";
	}*/
	
	@RequestMapping(value = "/documentFileUpLoad", method = RequestMethod.POST)
	public @ResponseBody String projectFileUpLoad(HttpSession session, String folder_name, 
			DocumentBoard documentBoard,
			MultipartFile upLoadFile,
			String file_name
			) {
		
		System.out.println("folder_name = "+folder_name);
		System.out.println(documentBoard.toString());
		System.out.println("file_name = "+file_name);
		//맨 처음 폴더이름을 받고, 다음 파일을 받고, board을 받는다.
		String resp = "success";
		String mainProjectNum = (String) session.getAttribute("mainproject_projectnum");
		System.out.println(mainProjectNum);
		//여기서는 프로젝트 id로 묶는다.
		
		//파일을 저장하거나 결과를 보낸다.
		String result = DocumentFileService.saveFile(upLoadFile, upLoadPath, file_name);
		
		System.out.println(result);
		
		//여기부턴 DB저장
		/*saveProjectFile.setProjectFile_member(loginId);
		int result = fileRepo.projectFileUpLoad(saveProjectFile);
		int projectFileNum = fileRepo.getFileNum();
		saveProjectFile.setProjectFile_num(projectFileNum);
		boolean bool=FileManager.saveFile(saveProjectFile, upLoadPath, upLoadFile);
		if (result == 0) {
			resp = "fail";
		}else if(!bool) {
			resp = "fail";
		}*/

		return resp;
	}
	
	
	@RequestMapping(value ="/detailPage", method = RequestMethod.GET)
	public String project_detail(Model model) {

		model.addAttribute("projectNum", "");
		
		return "project/detailPage";
	}

}
