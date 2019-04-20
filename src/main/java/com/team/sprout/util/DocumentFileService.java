package com.team.sprout.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.team.sprout.vo.DocumentBoard;
import com.team.sprout.vo.DocumentFile;

public class DocumentFileService {

	/**
	 * 업로드 된 파일을 지정된 경로에 저장하고, 저장된 파일명을 리턴
	 * @param mfile 업로드된 파일
	 * @param path 저장할 경로
	 * @return 저장된 파일명
	 */
	public static DocumentFile saveFileInfo(MultipartFile upload, String uploadPath, String inputedFilename) {
		//저장 폴더가 없으면 생성
		File path = new File(uploadPath);
		if (!path.isDirectory()) {
			path.mkdirs();
		}
		
		DocumentFile documentfile = new DocumentFile();
		
		//원본 파일명 : 파일이 존재하지 않으면 빈문자열 리턴
		String originalFilename = upload.getOriginalFilename();
		
		//원본 파일의 확장자와 파일명 분리
		String filename;		// 확장자를 뺀 파일명
		String ext;				// 확장명
		String savedFilename;	// 디스크에 저장할 이름
		
		int lastIndex = originalFilename.lastIndexOf('.');
		
		//확장자가 없는 경우
		if (lastIndex == -1) {
			ext = "";
			documentfile.setDocument_file_extension(ext);
			return documentfile;
		}
		
		//확장자가 있는 경우
		else {
			ext = "." + originalFilename.substring(lastIndex + 1);
			//이미지파일의 확장자가 아니라면 ㅂㅂㅇ
			if (!(ext.equals(".jpg")||ext.equals(".jpeg")||ext.equals(".png")||ext.equals(".bmp"))) {
				documentfile.setDocument_file_extension("");
				return documentfile;
			} else {
				documentfile.setDocument_file_extension(ext);
			}
			//filename= originalFilename.substring(0, lastIndex);
		}
		
		System.out.println("확장자 : "+ext);
		
		// DB에 저장될 파일명
		// savedFilename = filename+sdf.format(new Date()) + ext;
		
		filename = inputedFilename; // 확장명이 안붙은
		savedFilename = inputedFilename + ext; // 확장명이 붙은
		
		//HDD에 저장할 파일명. 같은 이름의 파일이 있는 경우의 처리
		/*while (true) {
			//같은 이름의 파일이 없으면 나감.
			if ( !serverFile.isFile()) break;	
			//같은 이름의 파일이 있으면 이름 뒤에 long 타입의 시간정보를 덧붙임.
			filename = filename + new Date().getTime();
		}*/
		
		//저장할 전체 경로를 포함한 File 객체
		File serverFile = null;	
		
		serverFile = new File(uploadPath + "/" + savedFilename);
		
		if (serverFile.isFile()) {
			documentfile.setDocument_file_extension("sameFile");
			return documentfile;
		}
		
		documentfile.setDocument_file_originalfileName(inputedFilename);
		documentfile.setDocument_file_location(uploadPath);
		
		return documentfile;
	}
	
	public static boolean saveFile(MultipartFile upload, String uploadPath, String savedFilename, String ext) {
		
		String Filename = savedFilename + ext;
		
		//저장할 전체 경로를 포함한 File 객체
				File serverFile = null;	
				
				serverFile = new File(uploadPath + "/" + Filename);
				
				boolean result = false;
		
		//파일 저장
				try {
					upload.transferTo(serverFile);  // 지정된 이름으로 지정된 위치에 파일 저장 
					result = true;
				} catch (Exception e) {
					savedFilename = null;
					e.printStackTrace();
					return result;
				}
		
		return result;
	}
	
	/**
	 * 서버에 저장된 파일의 전체 경로를 전달받아, 해당 파일을 삭제
	 * @param fullpath 삭제할 파일의 경로
	 * @return 삭제 여부
	 */
	public static boolean deleteFile(String fullpath) {
		//파일 삭제 여부를 리턴할 변수
		boolean result = false;
		
		//전달된 전체 경로로 File객체 생성
		File delFile = new File(fullpath);
		
		//해당 파일이 존재하면 삭제
		if (delFile.isFile()) {
			delFile.delete();
			result = true;
		}
		
		return result;
	}
}
