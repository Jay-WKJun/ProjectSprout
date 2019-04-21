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

import com.team.sprout.dao.DocumentFileManagerRepository;
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
	@Autowired
	DocumentFileManagerRepository dfmRepo;
	List<Member> projectMembers;
	
	final String upLoadPath = "/sproutDocumentFileManager";
	//기획... log를 저장해서 이곳에서 통계표를 만든다
	
	//통계표는 log 행동한 데이터의 종류를 숫자로 구분해서 나눈다. 거치는 controller 저장
	//등록 완료 중간저장 6시면 자동저장, 일일 진행사항 = 그 날 업무 할당량 달성정도
	//그 사람이 했다고 제출하면 관리자는 o x를 체크한다.

	// 파일 매니저 접속
	@RequestMapping(value = "/getFileList", method = RequestMethod.GET)
	public String fileLoad(HttpSession session) {
		String mainproject_projectnum = (String)session.getAttribute("mainproject_projectnum");
		//List<DocumentFolder> docuFolderList = dfmRepo.selectAllFolder(mainproject_projectnum);
		//ㅈㄴ 어렵겠네
		
		
		
		//map으로 가져오기, sql로 정제해서 가져온다.
		
		
		
		return null;
	}
	
	
	
	// 파일 매니저 접속
	@RequestMapping(value = "/documentManager", method = RequestMethod.GET)
	public String whiteBoard(Model model, String postitNumFromProjectNum) {
		//TODO:여기 구현
		//TODO:동시에  jsp수정
		//TODO:폴더 생성 메소드 구현
		
		//접속시 필요한 것
		//mainproject_projectnum으로 모든 폴더 찾기
		//각 폴더가 가진 파일들을 select형태로 풀어서 표현한다.
		//select의 첫 이름은 폴더의 이름이다.
		
		
		
		if (postitNumFromProjectNum != null) {
			model.addAttribute("projectNum", postitNumFromProjectNum);
		}
		return "project/fileManager";
	}
	
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
		
		
		String saveFullPath = upLoadPath + "/" + folder_name;
		
		//파일을 저장하거나 결과를 보낸다.
		//폴더를 구분하는 기준이 없다.
		DocumentBoard result = DocumentFileService.saveFileInfo(upLoadFile, saveFullPath, file_name);
		
		if (result.getDoucument_file_extension().equals("")) {
			resp="extender fail";
			return resp;
		}else if (result.getDoucument_file_extension().equals("sameFile")) {
			resp="have same file fail";
			return resp;
		}else {
			//DB저장 시작
			boolean saveResult = DocumentFileService.saveFile(upLoadFile, saveFullPath, file_name, result.getDoucument_file_extension());
			if (!saveResult) {
				return "save Fail";
			}
			
			int insertResult2 = dfmRepo.insertDocumentBoard(documentBoard);
			if (insertResult2 == 0) {
				return "board Insert Fail";
			}
			int Boardnum = dfmRepo.selectBoardNum();
			//folder가 없을때는 그냥 폴더이름만 받아서 진행하고 여기서 새로 만들면서 써준다.
			//불러올땐 그냥 title을 불러온다.
			DocumentFolder documentFolder = new DocumentFolder();
			documentFolder.setDocument_folder_title(folder_name);
			documentFolder.setDocument_board_num(Boardnum);
			documentFolder.setMainProject_projectNum(mainProjectNum);
			dfmRepo.insertDocumentFolder(documentFolder);
		}
		
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
