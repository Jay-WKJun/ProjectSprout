package com.team.sprout.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team.sprout.dao.FileManagerRepository;
import com.team.sprout.util.FileManager;
import com.team.sprout.vo.ProjectFile;

@Controller
public class FileManagerController {

	@Autowired
	FileManagerRepository fileRepo;

	final String upLoadPath = "/sproutProjectFileManager";
	
	// 파일 매니저 접속
	@RequestMapping(value = "/fileManager", method = RequestMethod.GET)
	public String whiteBoard(Model model, String postitNumFromProjectNum) {
		if (postitNumFromProjectNum != null) {
			model.addAttribute("projectNum", postitNumFromProjectNum);
		}
	
		return "project/fileManager";
	}
	
	@RequestMapping(value = "/projectFileUpLoad", method = RequestMethod.POST)
	public @ResponseBody String projectFileUpLoad(HttpSession session, ProjectFile projectFile,
			MultipartFile upLoadFile) {
		String resp = "success";
		String loginId = (String) session.getAttribute("loginId");
		
		ProjectFile saveProjectFile = FileManager.fileSetting(upLoadPath, projectFile, upLoadFile);
		saveProjectFile.setProjectFile_member(loginId);
		int result = fileRepo.projectFileUpLoad(saveProjectFile);
		int projectFileNum = fileRepo.getFileNum();
		saveProjectFile.setProjectFile_num(projectFileNum);
		boolean bool=FileManager.saveFile(saveProjectFile, upLoadPath, upLoadFile);
		if (result == 0) {
			resp = "fail";
		}else if(!bool) {
			resp = "fail";
		}

		return resp;
	}
	
	@RequestMapping(value = "/getFileList", method = RequestMethod.POST)
	public @ResponseBody List<ProjectFile> getFileList(String projectFile_location) {
		List<ProjectFile> list = fileRepo.getFileList(projectFile_location);
		return list;
	}

	@RequestMapping(value = "/projectFolderCreate", method = RequestMethod.POST)
	public @ResponseBody String projectFolderCreate(HttpSession session, ProjectFile projectFile) {
		String resp = "success";
		String loginId = (String) session.getAttribute("loginId");
		projectFile.setProjectFile_member(loginId);
		projectFile.setProjectFile_type(1);
		projectFile.setProjectFile_extension("");
		int result = fileRepo.projectFileUpLoad(projectFile);
		int projectFileNum = fileRepo.getFileNum();
		projectFile.setProjectFile_num(projectFileNum);
		ProjectFile saveProjectFile = FileManager.saveFolder(upLoadPath, projectFile);

		if (result == 0) {
			resp = "fail";
		}

		return resp;
	}
	
	@RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
	public String fileDownload(int projectFile_num, HttpServletResponse resp) {
		
		ProjectFile projectFile=fileRepo.searchFile(projectFile_num);
		String fileFullName=projectFile.getProjectFile_num()+"."+projectFile.getProjectFile_extension();
		String originalFIleName=projectFile.getProjectFile_originalname();
		String filePath=projectFile.getProjectFile_location()+"/"+fileFullName;
		
		try {
			resp.setHeader("Content-Disposition", "attatchment;filename="+URLEncoder.encode(originalFIleName, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		FileInputStream fis=null;
		ServletOutputStream fileOut=null;
		try {
			fis=new FileInputStream(upLoadPath+"/"+filePath);
			fileOut=resp.getOutputStream();
			FileCopyUtils.copy(fis, fileOut);
			fis.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/fileDelete", method = RequestMethod.POST)
	public @ResponseBody String fileDelete(int projectFile_num) {
		
		String resp="success";
		
		ProjectFile projectFile=fileRepo.searchFile(projectFile_num);
		String extension=projectFile.getProjectFile_extension();
		String filePath=projectFile.getProjectFile_location()+"/"+projectFile_num+"."+extension;
		
		boolean result=FileManager.deleteFile(upLoadPath+"/"+filePath);
		if(result) {
			int result2=fileRepo.fileDelete(projectFile_num);
			if(result2==0) {
				resp="fail";
			}
		}else {
			resp="fail";
		}
		
		return resp;
	}
	
	@RequestMapping(value = "/folderDelete", method = RequestMethod.POST)
	public @ResponseBody String folderDelete(int projectFile_num) {
		
		String resp="success";
		
		ProjectFile projectFile=fileRepo.searchFile(projectFile_num);
		String filePath=projectFile.getProjectFile_location()+"/"+projectFile.getProjectFile_originalname();
		
		System.out.println(upLoadPath+"/"+filePath);

		boolean result=FileManager.deleteFile(upLoadPath+"/"+filePath);
		if(result) {
			int result2=fileRepo.fileDelete(projectFile_num);
			if(result2==0) {
				resp="fail";
			}
		}else {
			resp="fail";
		}
		
		return resp;
	}

}
