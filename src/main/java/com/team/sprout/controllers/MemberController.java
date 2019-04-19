package com.team.sprout.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team.sprout.dao.MainProjectRepository;
import com.team.sprout.dao.MemberRepository;
import com.team.sprout.dao.ProjectMemberRepository;
import com.team.sprout.util.profileFile;
import com.team.sprout.vo.ChatRoom;
import com.team.sprout.vo.Member;

@Controller
public class MemberController {

	@Autowired
	MemberRepository repo;
	@Autowired
	MainProjectRepository mainrepo;
	@Autowired
	ProjectMemberRepository prrepo;

	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
	public @ResponseBody int checkId(String member_id) {
		System.out.println(member_id + "아이디체크를 위한 멤버아이디 에이젝스에서 받기중");
		Member result = repo.checkId(member_id);
		if (result == null) {
			return 0; // 사용가능
		}
		return 1;
	}

	/*
	 * join GET
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Member member) {
		return "member/joinForm";
	}

	/*
	 * join POST
	 */
	@RequestMapping(value = "/join", method = RequestMethod.POST) 
	public String joinP(Member member, MultipartFile upload) {
		
		profileFile pro = new profileFile();// 프로필 사진을 달기 위한 선언자
		
		if (upload.isEmpty() != true) {
			pro.uploadfile(upload, member); // file이랑 member를 가지고 넘어가서 저장까지.
			System.out.println("------------- 회원가입 with 프로필 사진 O -------------");
			System.out.println(member.toString());
		} else {
			member.setMemberImage_saveAddress(null);
			System.out.println("------------- 회원가입 with 프로필 사진 X -------------");
			System.out.println(member.toString());
		}
		
		/*int result = repo.memberJoin(member); // 회원가입
		System.out.println("회원가입 결과 : " + result); // 회원가입 결과 확인
*/
		return "redirect:/";
	}

	/*
	 * login GET
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "member/loginForm";
	}

	
	
	/*
	 * login POST
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginP(Member member, boolean idChecked,
			@CookieValue(value = "idChecked", defaultValue = "") String uid, Model model, HttpSession session,
			HttpServletResponse response) {

		if (idChecked) {
			// idCheck를 눌럿다면
			Cookie cookie = new Cookie("idChecked", member.getMember_id());
			// ()안에는 초단위 int, 24시간동안 cookie를 유지하라는 얘기
			cookie.setMaxAge(24 * 60 * 60);
			// HDD에 쿠키에 저장하라는 명령이다.
			response.addCookie(cookie);
		} else { // 쿠키 삭제해야함
			Cookie cookie = new Cookie("idChecked", null);
			// expire time = 0 = 즉시 지워라
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		Map<String, String> map = new HashMap<>();

		map.put("id", member.getMember_id());
		map.put("password", member.getMember_password());

		// 입력된 정보로 DB가서 그 정보를 가져온다.
		Member m = repo.selectOne(map);
		System.out.println("member아이디"+member.getMember_id());
		Member getMember = repo.selectOneWebsocket(member.getMember_id());
		
		// 로그인 성공
		String message = null;
		if (getMember == null || !(getMember.getMember_id().equals(member.getMember_id()) && getMember.getMember_password().equals(member.getMember_password()))) {
			message = "아이디 혹은 비밀번호가 맞지 않습니다.";
			model.addAttribute("message", message);
			return "member/loginForm";
		} else {
			System.out.println("로그인 성공 **********************");
			session.setAttribute("loginId", getMember.getMember_id());
			session.setAttribute("loginName", getMember.getMember_name());
			session.setAttribute("loginNum", getMember.getMember_num());
			session.setAttribute("member_num", getMember.getMember_num());
			// ---------------------------------------------------- login할 때마다 DB에 로그인 시간 잡아주기
			repo.setLoginTime(getMember.getMember_id());
			// ----------------------------------------------------- login 후 이미지(파일) 불러오기
			String mime = null; // mime은 사진 형식인지 확인하기 위한것..... (image/jpeg)
			String fullPath = m.getMemberImage_saveAddress(); // profile_img가 저장된 위치.

			if (fullPath == null) { // 회원가입 할 때 프로필 사진을 지정안함
				System.out.println("회원가입할 때 프로필 사진 지정을 안함");
			} else {
				try {
					mime = Files.probeContentType(Paths.get(fullPath));
					mime.contains("image"); // 해당 문자열 안에 내가 원하는 문자가 포함되어 있는가
					session.setAttribute("mime", mime); // .jsp에 가져갈 이미지 파일을 담았다.
				} catch (IOException e) { e.printStackTrace(); }
			} // if & else
			// ----------------------------------------------------- 환.
		}
		return "redirect:/";
	}
	
	/*
	 * ID로 img파일 불러오기. (메인화면에 img 띄우기.)
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET) // anchor tag
	public void download(String loginId, HttpServletResponse response) {
		Member m = repo.checkId(loginId);// 재사용.
		String fullPath = m.getMemberImage_saveAddress();

		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		if(fullPath != null) {
			try {
				filein = new FileInputStream(fullPath);// must to checked by try & catch
				fileout = response.getOutputStream();
				FileCopyUtils.copy(filein, fileout);
				
				filein.close();
				fileout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}// 이거 지우면 프로필 사진 못불러옴 (by 환.)

	/*
	 * logout GET
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		System.out.println("logout!!");
		// 모든 session 제거
		session.invalidate();

		return "redirect:/";
	}

	/*
	 * googleSignInCallback POST
	 */
	@RequestMapping(value = "/googleSignInCallback", method = RequestMethod.POST)
	public @ResponseBody String doSessionAssignActionPage(Member member) throws Exception {
		System.out.println(member.toString());

		// DB에 연결합니다.
		// member id 로 member를 찾아 올 수 있는 메소드가 필요하다
		// 구글 로그인해서 찾아온 정보의 id로 이미 회원가입이 되어 있는 사람을 찾아온다.찾아 온 member 그대로 쏜다.
		Member memberVali = repo.checkId(member.getMember_id());
		if (memberVali == null) {
			// 만약 같은 아이디의 회원이 없다면 가입시킨다.
			member.setMember_address("google");
			member.setMember_phone(0);
			member.setMemberImage_saveAddress("none");
			int result = repo.memberJoin(member);
			System.out.println("Google 로그인한 사람 회원 등록 완료" + result);
			memberVali = repo.checkId(member.getMember_id());
		}

		return memberVali.getMember_id();
	}

	/*
	 * ID를 사용해 DB에서 정보 불러오기
	 */
	@RequestMapping(value = "/googleChecked", method = RequestMethod.GET)
	public String googleLogin(String memberId, HttpSession session) {
		System.out.println(memberId.toString());
		Member member = repo.checkId(memberId);

		System.out.println("google Login 하러 왔음");
		System.out.println(member.toString());
		session.setAttribute("loginNum", member.getMember_num());
		session.setAttribute("loginId", member.getMember_id());
		session.setAttribute("loginName", member.getMember_name());

		return "redirect:/";
	}

	/*
	 * 회원정보 페이지로 이동.
	 */
	@RequestMapping(value = "/memberInfo", method = RequestMethod.GET) // ------------------------ ㅠㅠ(아직 진행중.)
	public String memberInfo(Member meber, HttpSession session, Model model) {
		String loginId = (String) session.getAttribute("loginId"); // session에 저장된 id 불러오기.
		
		System.out.println("--- 회원정보 page (/memberInfo.GET)---");
		System.out.println("session에 저장된 id : " + loginId);
		System.out.println();
		
		Member member = repo.checkId(loginId);

		String mime = null; // mime은 사진 형식인지 확인하기 위한것..... (image/jpeg)
		String fullPath = member.getMemberImage_saveAddress(); // profile_img가 저장된 위치.

		if (member.getMemberImage_saveAddress() == null) { // 회원가입 할 때 프로필 사진을 지정안함
			System.out.println("회원가입시 사진 사용안함.");
		} else {
			try {
				mime = Files.probeContentType(Paths.get(fullPath));
				if (mime.contains("image")) { // 해당 문자열 안에 내가 원하는 문자가 포함되어 있는가
					session.setAttribute("mime", mime); // .jsp에 가져갈 이미지 파일을 담았다.
				}else {
					System.out.println("hhhh");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // if & else
		session.setAttribute("member", member);

		return "member/memberInfo";
	}

	/*
	 * 회원정보 수정 POST
	 * process - 세션(ID)을 통해 회원정보 불러오기(파일을 삭제하기 위해) - 기존파일 삭제 - 새파일 입력 - 회왼정보 update 
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Member member, HttpSession session, MultipartFile newPicture) {
		// ------------------------------------------------------------ 세션에 ID로 회원정보 불러오기
		profileFile pro = new profileFile();// 프로필 사진을 달기 위한 선언자

	String id = (String) session.getAttribute("loginId");
		Member session_info = repo.checkId(id); // 기본 session에 저장된 ID로 뽑아온 정보
		
		System.out.println("---------- information of modify ----------");
		System.out.println("session_info >> "+ session_info);	// old info in session     (id)
		System.out.println("new member   >> "+ member);			// new info as MEMBER type (name, pw, phone, add, old file info)
		System.out.println("oldPicture   >> "+ session_info.getMemberImage_saveAddress());
		System.out.println("newPicture   >> "+ newPicture);
		System.out.println("===========================================");
		
		
		
//-------------------------------------------------------------------------------| detail 작업 예정 <환> |	
		/*String old_picture = session_info.getMemberImage_saveAddress();
		
		if(old_picture != null) {
			if(newPicture.isEmpty() != true) {
			} else {
			}
		} else { // old_picture == null
			if(newPicture.isEmpty() != true) {
			} else {
			}
		}*/
//------------------------------------------------------------------------------ | do not touch |	
		
// 0. 기존의 프로필 사진 O  --> 삭제 X (ajax로 따로) 후에는 2번이나 3번으로 시작한다.
		
		if (newPicture.isEmpty() != true) { 						// 입력받은 프로필 사진이 있다.
			if(session_info.getMemberImage_saveAddress() != null){	// 기존의 프로필 사진이 있다. 
// 1. 기존의 프로필 사진 O  --> 새로운 프로필 사진 O
				System.out.println(">> 기존의 프로필 사진 O  --> 새로운 프로필 사진 O");
				// ------------------------------------------------------------ 내가 필요한 getMemberImage_saveAddress 찾기
				String oldPath = session_info.getMemberImage_saveAddress();
				System.out.println("삭제하려고 하는 파일의 fullPath : "+ oldPath);
				// ------------------------------------------------------------ getMemberImage_saveAddress으로 파일 삭제
				pro.deletefile(oldPath); //실제적으로 삭제하는 부분.
			// ------------------------------------------------------------ 서버에 새로운 파일 저장하기.
				String newPAth = pro.uploadfile(newPicture, session_info);// 이름은 같지만 확장자가 바뀔 상황이 있어서 같이 잡아줘야 한다.
				// ------------------------------------------------------------ DB에 회원정보 입력
				member.setMemberImage_saveAddress(newPAth);
				repo.updateMember(member);
				
				String mime = null;
				String fullPath = member.getMemberImage_saveAddress();
				try {
					mime = Files.probeContentType(Paths.get(fullPath));
				} catch (IOException e) { e.printStackTrace(); }
				mime.contains("image"); // 해당 문자열 안에 내가 원하는 문자가 포함되어 있는가
				session.setAttribute("mime", mime);
				
			} else {												// 기존의 프로필 사진이 없다.
// 2. 기존의 프로필 사진 X  --> 새로운 프로필 사진 O
				System.out.println(">> 기존의 프로필 사진 X  --> 새로운 프로필 사진 O");

				String newPath = pro.uploadfile(newPicture, session_info);
				System.out.println("--- to check ---");
				System.out.println("newPath : "+ newPath);
				
				member.setMemberImage_saveAddress(newPath);
				
				System.out.println("member : "+member.toString());
				repo.updateMember(member);
				
				String mime = null; // mime은 사진 형식인지 확인하기 위한것..... (image/jpeg)
				String fullPath = member.getMemberImage_saveAddress(); // profile_img가 저장된 위치.
				try {
					mime = Files.probeContentType(Paths.get(fullPath));
				} catch (IOException e) { e.printStackTrace(); }
				mime.contains("image"); // 해당 문자열 안에 내가 원하는 문자가 포함되어 있는가
				session.setAttribute("mime", mime);
			}
		}else {
// 3. 기존의 프로필 사진 X  --> 새로운 프로필 사진 X
			System.out.println(">> 기존의 프로필 사진 X  --> 새로운 프로필 사진 X");
			member.setMemberImage_saveAddress(null);
			System.out.println(member.toString());
			repo.updateMember(member);
		}
// 4. 아무것도 안건드리고 수정 버튼을 누름. 이런 기능 없음ㅋㅋ 같은 값을 입력해도 무조건 입력 해야함.
		
		return "redirect:/";
	} // modify

	/*
	 * del profile picture with ajax  (by.whan
	 */
	@RequestMapping(value = "/del_pro", method = RequestMethod.GET)
	public @ResponseBody String del_pro(HttpSession session) {
		profileFile pro = new profileFile(); 
		
		String id = (String) session.getAttribute("loginId");
		Member session_info = repo.checkId(id); 					// 기본 session에 저장된 ID로 뽑아온 정보
		
		if(session_info.getMemberImage_saveAddress() != null) {
			pro.deletefile(session_info.getMemberImage_saveAddress()); 	// C에서 프로필 사진 지우기
			repo.setNull_profile(id); 									// DB에서 삭제하기(null 설정하기)
			String mime = null;
			session.setAttribute("mime", mime); //?
			return "success";					//?
		} else {
			System.out.println("사진이 원래 없음 (/del_pro)");
			return null;
		}
	}
	
	@RequestMapping(value = "/delete_info", method = RequestMethod.GET)
	public String delete_info(HttpSession session) {
		profileFile pro = new profileFile(); 
		
		String id = (String) session.getAttribute("loginId");
		Member session_info = repo.checkId(id); 					// 기본 session에 저장된 ID로 뽑아온 정보
		if (session_info.getMemberImage_saveAddress()!=null) {
			pro.deletefile(session_info.getMemberImage_saveAddress()); 	// C에서 프로필 사진 지우기
		}
		repo.deleteMember(id);
		
		String mime = null;
		session.setAttribute("mime", mime);
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/multiChatRoom", method = RequestMethod.GET)
	public String multiChatRoom(int chatRoom_num, String chatRoom_name, ChatRoom chatroom, Model model, HttpSession session) {
		System.out.println("asdasdsad"+chatRoom_num);	
			ChatRoom cr = new ChatRoom();
			cr.setChatRoom_name(chatRoom_name);
			cr.setChatRoom_num(chatRoom_num);
	
			System.out.println("cr: "+cr.toString());
			int result =repo.insertRoomnum(cr);//name
		/*	int results = repo.selectRoomnum();
			chatroom.setChatRoom_num(results);
			model.addAttribute("chatRoom_num", chatroom.getChatRoom_num());
		session.setAttribute("chatRoom_num", chatroom.getChatRoom_num());*/
			session.setAttribute("chatRoom_num", chatRoom_num);
			session.setAttribute("chatRoom_name", chatRoom_name);
			model.addAttribute("chatRoom_namess", chatRoom_name);
			return "websocket/multiChatRoom";
	}
	
	/*
	 * 채팅방 회원초대를 위해 아이디로 membernum가져오기
	 */
	
	@RequestMapping(value = "/selectMemberNum", method = RequestMethod.POST)
	public @ResponseBody String selectMemberNum(String member_name, HttpSession session, Model model) {
				
			
		String member_nameOne = repo.selectOneMemberNum(member_name);
		System.out.println("tq"+member_nameOne);
		model.addAttribute("member_namess", member_name);
		System.out.println("맴버아이디 상대편꺼 "+member_name);
		model.addAttribute("Click_member_name", member_name);
		return member_nameOne;
	}
	
//	@RequestMapping(value = "/ClickselectMemberNum", method = RequestMethod.POST)
//	public @ResponseBody String ClickselectMemberNum(String member_name, HttpSession session, Model model) {
//				
//			
//		String member_nameOne = repo.selectOneMemberNum(member_name);
//		System.out.println("tq"+member_nameOne);
//		model.addAttribute("member_namess", member_name);
//		System.out.println("맴버아이디 상대편꺼 "+member_name);
//		model.addAttribute("Click_member_name", member_name);
//		return member_nameOne;
//	}
	
	@RequestMapping(value = "/ClickselectMemberNums", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, String> ClickselectMemberNum(String member_name, HttpSession session, Model model) {
		HashMap<String, String> map = new HashMap<>();
		String member_nameOne = repo.selectOneMemberNum(member_name);
		model.addAttribute("member_namess", member_name);
		map.put("member_num", member_nameOne);
		map.put("member_name", member_name);
		return map;
	}
	
}
