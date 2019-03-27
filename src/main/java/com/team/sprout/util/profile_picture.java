package com.team.sprout.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.team.sprout.vo.Member;

public class profile_picture {
	
	final String upload_path = "/memberProfile"; //----------------- EC2 서버에서 프로필이 저장 될 directory 위치.
	
	public String picture(MultipartFile file, Member member){
		System.out.println("------------- profile_ficture -------------");
		System.out.println(file);
		System.out.println(member);
		System.out.println("-------------------------------------------");
		System.out.println();
		
		// original file name. //눌값 
		String Original_name = file.getOriginalFilename();
		// --------------------------------------------------------- 원본 파일의 확장자와 파일명 분리하기 위한 변수 setting
		int filename; // 확장자를 뺀 파일명
		String ext; // 확장명
		String savedFilename; // 최종 파일명.
		
		int lastIndex = Original_name.lastIndexOf('.');
		
		//----------------------------------------------------------- | 확장자 검사 |
		// 확장자가 없는 경우는 memberImg를 바로 setting 해주면 된다.
		if (lastIndex == -1) {
			ext = "";
			filename = member.getMember_num();  
		}
		// 확장자가 있는 경우 
		else {
			ext = "." + Original_name.substring(lastIndex + 1);  	// 확장자를 뺀다.
			//filename = Original_name.substring(0, lastIndex);		// 확장자가 빠진 순순 파일 이름.
			filename = member.getMember_num(); 			// 계정 정보를 파일 이름으로 setting 해준다.
		}
		//-----------------------------------------------------------
		
		//  세탁된 file name + 확장자.
		savedFilename = filename + ext;		
		System.out.println("----------------------------------------");		
		System.out.println("| original File name -> " + Original_name);
		System.out.println("| setting File name -> " + savedFilename);
		System.out.println("----------------------------------------");
		
		File serverFile = null;
		serverFile = new File(upload_path + "/" + savedFilename);
		
		String inputname = upload_path+"/"+ savedFilename;

		try {
			file.transferTo(serverFile); // 지정된 이름으로 지정된 위치에 파일 저장
			System.out.println("파일 이름 성공적으로 변경 ^^^^^^^^^^^^^^^^^^^^^^^");
		} catch (Exception e) {
			System.out.println("파일 이름 변경 실패          ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ");
			e.printStackTrace();
		}
		
		return inputname;
	}
	
}
