package com.team.sprout.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.team.sprout.vo.Member;
import com.team.sprout.vo.WantedBoard;

public class profileFile {

	final String upload_path_for_img = "/memberProfile"; // ------- EC2 서버에서 프로필이 저장 될 directory 위치.
	
	/*  프로필 사진 upload하기 방식 : 파일의 이름으로 member.getMember_name()을 설정하고 C에 저장한다. */
	public String uploadfile(MultipartFile file, Member member) {
		// -------------------------------------------------- 원본 파일의 확장자와 파일명을 분리하기 위한
		// 변수 setting
		String Original_name = file.getOriginalFilename(); // 파일의 원래 이름.
		String filename; // 확장자를 뺀 파일명
		String ext; // 확장명
		String savedFilename; // 최종 파일명.
		File serverFile = null; // 파일 올리기.
		int lastIndex = Original_name.lastIndexOf('.'); //

		if (lastIndex == -1) { // ---------------------------- 확장자가 없는 경우는 memberImg를 바로 setting 해주면 된다.
			ext = "";
			filename = member.getMember_id();
		} else { // -------------------------------------------- 확장자가 있는 경우
			ext = "." + Original_name.substring(lastIndex + 1); // 확장자를 뺀다.
			filename = member.getMember_id(); // 계정 정보를 파일 이름으로 setting 해준다.
		}

		savedFilename = filename + ext; // savedFilename은 세탁된 file name + 확장자.
		serverFile = new File(upload_path_for_img + "/" + savedFilename);

		try {
			file.transferTo(serverFile); // 지정된 이름으로 지정된 위치에 파일 저장
			System.out.println("try & catch 결과 EC2에 저장 성공");
		} catch (Exception e) {
			System.out.println("try & catch 결과  EC2에 저장 실패");
			e.printStackTrace();
		}

		String location = upload_path_for_img + "/" + savedFilename;
		member.setMemberImage_saveAddress(location); // memberImage_saveAddress의 변수를 location으로 설정한다.
		return location;
	}// public void picture

	/*
	 * 프로필 사진 renew하기 
	 * process : 기존의 프로필 사진을 삭제하기
	 */
	public void deletefile(String fullPath) { // ------------------------ 프로필 사진 renew하기 [제작 중.]
		
		File delFile = new File(fullPath);

		if (delFile.isFile()) {
			delFile.delete();
		}

	}
}
