package com.team.sprout.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.team.sprout.vo.DocumentFile;
import com.team.sprout.vo.DocumentFolder;
import com.team.sprout.vo.MainProject;
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
	// 기획... log를 저장해서 이곳에서 통계표를 만든다

	// 통계표는 log 행동한 데이터의 종류를 숫자로 구분해서 나눈다. 거치는 controller 저장
	// 등록 완료 중간저장 6시면 자동저장, 일일 진행사항 = 그 날 업무 할당량 달성정도
	// 그 사람이 했다고 제출하면 관리자는 o x를 체크한다.

	// 파일 매니저 접속
	@RequestMapping(value = "/detailPage", method = RequestMethod.GET)
	public String detailPage(Model model, HttpSession session) {
		String mainproject_projectnum = (String)session.getAttribute("mainproject_projectnum");
		
		List<MainProject> mainProjects = prrepo.findProjectName(mainproject_projectnum);
		for (MainProject mainProject : mainProjects) {
				model.addAttribute("MainProject_title", mainProject.getMainproject_title());
		}
		
		return "project/detailPage";

	}
	
	/*
	 * 모든 폴더리스트와 board를 가져온다.
	 */
	@RequestMapping(value = "/loadList", method = RequestMethod.GET)
	@ResponseBody
	public List<Map> loadList(HttpSession session) {

		// 접속시 필요한 것
		// mainproject_projectnum으로 모든 폴더 찾기
		// 각 폴더가 가진 파일들을 select형태로 풀어서 표현한다.
		// select의 첫 이름은 폴더의 이름이다.
		
		String mainproject_projectnum = (String)session.getAttribute("mainproject_projectnum");

		// 이곳에 모든 원기옥을 모은다.
		List<Map> map = new ArrayList<>();
		
		List<DocumentFolder> folderList = dfmRepo.selectfolderList(mainproject_projectnum);

		for (DocumentFolder documentFolder : folderList) {
			// 이곳에 처음엔 folder의 이름, 그 이후엔 해당 board리스트가 들어간다.
			Map<String, Object> result = new HashMap<>();

			result.put("folderName", documentFolder.getDocument_folder_title());

			List<DocumentBoard> boardList = dfmRepo.selectSameFolderBoardList(documentFolder.getDocument_folder_num());

			result.put("boardList", boardList);

			map.add(result);
		}

		for (Map map2 : map) {
			System.out.println("folderName : " + (String) map2.get("folderName"));
			for (DocumentBoard documentBoard : (List<DocumentBoard>) map2.get("boardList")) {
				System.out.println(documentBoard.toString());
			}
			System.out.println("---------------------------------------------");
		}
		
		return map;

	}

	//파일 저장.
	@RequestMapping(value = "/documentFileUpLoad", method = RequestMethod.POST)
	public @ResponseBody String projectFileUpLoad(HttpSession session, String folder_name, DocumentBoard documentBoard,
			MultipartFile upLoadFile, String file_name) {
		
		
		String mainproject_projectnum = (String)session.getAttribute("mainproject_projectnum");
		// 맨 처음 폴더이름을 받고, 다음 파일을 받고, board을 받는다.
		String resp = "success";
		// 여기서는 프로젝트 id로 묶는다.

		DocumentFolder selectedDocumentFolder = dfmRepo.selectFolderNum(mainproject_projectnum, folder_name);
		
		//받아온 정보로 찾아온게 없다면 저장한다.
		if (selectedDocumentFolder == null) {
			resp = "no Folder";
			return resp;
		}

		documentBoard.setDocument_folder_num(selectedDocumentFolder.getDocument_folder_num());

		// folder의 번호찾기 (title로 찾는다)
		// 집어넣고 insert

		DocumentBoard selectedDocumentboard = dfmRepo.selectBoardNum(documentBoard.getDocument_board_title());

		if (selectedDocumentboard == null) {
			//board를 찾아서 없으면 insert한다.
			int insertResult2 = dfmRepo.insertDocumentBoard(documentBoard);

			if (insertResult2 == 0) {
				return "board Insert Fail";
			}
			
			// 저장했으니까 갱신
			selectedDocumentboard = dfmRepo.selectBoardNum(documentBoard.getDocument_board_title());

			String saveFullPath = upLoadPath + "/" + mainproject_projectnum + "/" + folder_name;

			// 파일을 저장하거나 결과를 보낸다.
			// 폴더를 구분하는 기준이 없다.
			DocumentFile result = DocumentFileService.saveFileInfo(upLoadFile, saveFullPath, file_name);

			if (result.getDocument_file_extension().equals("")) {
				resp = "extender fail";
				return resp;
			} else if (result.getDocument_file_extension().equals("sameFile")) {
				resp = "have same file fail";
				return resp;
			} else {
				// 실제 저장 시작
				boolean saveResult = DocumentFileService.saveFile(upLoadFile, saveFullPath, file_name,
						result.getDocument_file_extension());
				if (!saveResult) {
					return "save Fail";
				}

				result.setDocument_board_num(selectedDocumentboard.getDocument_board_num());
				int insertResult = dfmRepo.insertDocumentFile(result);

				if (insertResult == 0) {
					return "file Insert Fail";
				}

				// folder가 없을때는 그냥 폴더이름만 받아서 진행하고 여기서 새로 만들면서 써준다.
				// 불러올땐 그냥 title을 불러온다.

			}

			return resp;
		} else {
			// 같은 제목 board를 찾았는데 있으면 boardinsert없이 진행한다.

			String saveFullPath = upLoadPath + "/" + mainproject_projectnum + "/" + folder_name;

			// 파일을 저장하거나 결과를 보낸다.
			// 폴더를 구분하는 기준이 없다.
			DocumentFile result = DocumentFileService.saveFileInfo(upLoadFile, saveFullPath, file_name);

			if (result.getDocument_file_extension().equals("")) {
				resp = "extender fail";
				return resp;
			} else if (result.getDocument_file_extension().equals("sameFile")) {
				resp = "have same file fail";
				return resp;
			} else {
				// 실제 저장 시작
				boolean saveResult = DocumentFileService.saveFile(upLoadFile, saveFullPath, file_name,
						result.getDocument_file_extension());
				if (!saveResult) {
					return "save Fail";
				}

				result.setDocument_board_num(selectedDocumentboard.getDocument_board_num());
				int insertResult = dfmRepo.insertDocumentFile(result);

				if (insertResult == 0) {
					return "file Insert Fail";
				}

				// folder가 없을때는 그냥 폴더이름만 받아서 진행하고 여기서 새로 만들면서 써준다.
				// 불러올땐 그냥 title을 불러온다.

			}

			return resp;
		}

	}
	
	/*
	 * 여기서 내가 선택한 board의 글과 그림들을 가져온다.
	 */
	@RequestMapping(value = "/loadBoard", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> loadBoard(int board_num) {
		
		HashMap<String, Object> result = new HashMap<>();
		
		DocumentBoard selectedOneBoard = dfmRepo.selectOneBoard(board_num);
		
		List<DocumentFile> selectedfiles = dfmRepo.selectFiles(board_num);
		
		for (DocumentFile documentFile : selectedfiles) {
			System.out.println(documentFile.toString());
		}
		
		result.put("boardTitle", selectedOneBoard.getDocument_board_title());
		result.put("boardContent", selectedOneBoard.getDocument_board_content());
		result.put("files", selectedfiles);
		
		return result;
	}
	
	//폴더생성
	@RequestMapping(value = "/createFolderForm", method = RequestMethod.POST)
	public String createFolderForm(String folderNameInput, HttpSession session) {
		
		String mainproject_projectnum = (String)session.getAttribute("mainproject_projectnum");
		DocumentFolder selectedDocumentFolder = dfmRepo.selectFolderNum(mainproject_projectnum, folderNameInput);
		
		//찾았는데 없다면 저장한다.
		if (selectedDocumentFolder == null) {
			DocumentFolder documentFolder = new DocumentFolder();
			documentFolder.setDocument_folder_title(folderNameInput);
			documentFolder.setMainProject_projectNum(mainproject_projectnum);
			dfmRepo.insertDocumentFolder(documentFolder);
		}
		
		return "redirect:/detailPage";
	}

	/*
	 * @RequestMapping(value ="/detailPage", method = RequestMethod.GET) public
	 * String project_detail(Model model) {
	 * 
	 * model.addAttribute("projectNum", "");
	 * 
	 * return "project/detailPage"; }
	 */

}
