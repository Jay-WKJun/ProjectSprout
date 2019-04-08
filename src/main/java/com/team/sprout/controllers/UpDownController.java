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

import com.team.sprout.dao.UpDownDAO;
import com.team.sprout.vo.UpDown;



@Controller
public class UpDownController {
	@Autowired
	UpDownDAO dao;
	final String uploadPath = "/boardfile";
	
	
	@RequestMapping (value="write", method=RequestMethod.POST)
    public @ResponseBody UpDown write(            
    		/*"text/plain; chartset=utf-8"
    		 * , produces="application/plain;charset=UTF-8"
    		 * */

    		UpDown updown,      
          MultipartFile upload,      
          HttpSession session,      
          Model model) throws UnsupportedEncodingException {      

       //첨부파일이 있는 경우 지정된 경로에 저장하고, 원본 파일명과 저장된 파일명을 Board객체에 세팅         
		String savedfile = FileService.saveFile(upload, uploadPath);
		String s = new String(savedfile.getBytes("iso-8859-1"),"UTF-8");
		if (!upload.isEmpty()) {         
          updown.setOriginalfile(upload.getOriginalFilename()); 
          updown.setSavedfile(s);      
          dao.updownRegist(updown);
          int result = dao.selectUpdownNum();
          updown.setUpdown_num(result);
         
          
       }
       return updown;
	}
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public String download(int updown_num, HttpServletResponse response) {
		//System.out.println(board);
			//boardnum에 해당하는 글 추출
			UpDown updown = dao.seletOne(updown_num);
						/*	int aa = updown.getUpdown_num();
							UpDown updown2 = dao.seletOnes(aa);
							System.out.println(updown2);*/
			//파일명을 추출
			String originalfile = updown.getOriginalfile();
			String savedfiles 	= updown.getSavedfile();
			String fullpath     = uploadPath + "/" + savedfiles;			
			try {
				response.setHeader("Content-Disposition", "attatchment;filename="
						+ URLEncoder.encode(originalfile, "UTF-8")
						);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			FileInputStream filein = null;
			ServletOutputStream fileout = null;
			try {
				filein = new FileInputStream(fullpath);
				fileout = response.getOutputStream();
				FileCopyUtils.copy(filein, fileout);
				
				filein.close();
				fileout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		return null;

	}
	
	
}
