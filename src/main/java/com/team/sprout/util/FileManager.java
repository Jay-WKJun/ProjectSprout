package com.team.sprout.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.team.sprout.vo.ProjectFile;

public class FileManager {

	public static ProjectFile fileSetting(String upLoadPath, ProjectFile projectFile, MultipartFile upLoadFile) {
		File path = new File(upLoadPath + "/" + projectFile.getProjectFile_location());
		if (!path.isDirectory()) {
			path.mkdirs();
		}

		projectFile.setProjectFile_type(2);

		String originalFilename = upLoadFile.getOriginalFilename();
		projectFile.setProjectFile_originalname(originalFilename);

		if (originalFilename.trim().length() == 0 || upLoadFile.isEmpty()) {
			return projectFile;
		}

		// 원본 파일의 확장자와 파일명 분리
		String ext = null; // 확장명

		int lastIndex = originalFilename.lastIndexOf('.');

		// 확장자가 없는 경우
		if (lastIndex == -1) {
			projectFile.setProjectFile_extension("");
		}

		// 확장자가 있는 경우
		else {
			ext = originalFilename.substring(lastIndex + 1);
			projectFile.setProjectFile_extension(ext);
		}
		return projectFile;
	}
	
	public static boolean saveFile(ProjectFile projectFile,String upLoadPath,MultipartFile upLoadFile) {
		boolean result = true;
		// 저장할 전체 경로를 포함한 File 객체
		File serverFile = null;
		
		serverFile = new File(upLoadPath + "/" + projectFile.getProjectFile_location() + "/"
				+ projectFile.getProjectFile_num()+"."+projectFile.getProjectFile_extension());

		try {
			upLoadFile.transferTo(serverFile); // 지정된 이름으로 지정된 위치에 파일 저장
		} catch (Exception e) {
			e.printStackTrace();
			result=false;
		}
		return result;
	}

	public static ProjectFile saveFolder(String upLoadPath, ProjectFile projectFile) {
		File path = new File(upLoadPath + "/" + projectFile.getProjectFile_location() + "/"
				+ projectFile.getProjectFile_originalname());

		if (!path.isDirectory()) {
			path.mkdirs();
		}
		return projectFile;
	}
	
	public static boolean deleteFile(String path) {
		// 파일 삭제 여부를 리턴할 변수
		boolean result = false;

		// 전달된 전체 경로로 File객체 생성
		File delFile = new File(path);

		// 해당 파일이 존재하면 삭제
		if (delFile.isFile()||delFile.isDirectory()) {
			delFile.delete();
			result = true;
		}
		
		return result;
	}

}
