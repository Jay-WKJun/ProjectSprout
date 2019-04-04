package com.team.sprout.controllers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team.sprout.dao.MainProjectRepository;
import com.team.sprout.dao.MemberRepository;
import com.team.sprout.dao.ProjectMemberRepository;
import com.team.sprout.util.profile_picture;
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

	
	@ResponseBody
	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
	public int checkId(String member_id) {
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
	
		profile_picture pro = new profile_picture();// 프로필 사진을 달기 위한 선언자
		pro.picture(upload, member); 			// file이랑 member를 가지고 넘어가서 저장까지.	
		
		String inputname = upload.getOriginalFilename(); // 이거 하려면 profile_picture 고쳐야함.
		member.setMemberImage_saveAddress(inputname);
		
		int result = repo.memberJoin(member);		// 회원가입 
		System.out.println("회원가입 결과 : "+result);	// 회원가입 결과 확인 

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

		/*Map<String, String> map = new HashMap<>();

		map.put("id", id);
		map.put("password", password);

		// 입력된 정보로 DB가서 그 정보를 가져온다.
		Member m = repo.selectOne(map);*/
		Member getMember = repo.selectOne(member.getMember_id());
		
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
			session.setAttribute("member_num", getMember.getMember_num());
			System.out.println(getMember.getMember_num());
		}
		return "redirect:/";
	}

	/*
	 * update GET
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Member member, HttpSession session, Model model) {
/*
		String id = (String) session.getAttribute("loginId");
		String password = (String) session.getAttribute("loginPassword");
*//*
		Map<String, String> map = new HashMap<>();

		map.put("id", id);
		map.put("password", password);*/

		Member getMember = repo.selectOne(member.getMember_id());

		model.addAttribute("num", getMember.getMember_num());
		model.addAttribute("id", getMember.getMember_id());
		model.addAttribute("password", getMember.getMember_password());
		model.addAttribute("name", getMember.getMember_name());
		model.addAttribute("phone", getMember.getMember_phone());
		model.addAttribute("address", getMember.getMember_address());

		return "member/updateForm";
	}

	/*
	 * update POST 실제 작동부분
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateP(Member member, HttpSession session) {

		System.out.println(member.toString());
		int result = repo.updateMember(member);
		// 확인용
		System.out.println(result);

		session.invalidate();

		return "redirect:/";
	}

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
	
	@RequestMapping(value = "/googleSignInCallback", method = RequestMethod.POST)
    @ResponseBody
    public String doSessionAssignActionPage(Member member) throws Exception {
    	System.out.println(member.toString());
    	
		//DB에 연결합니다.
    	//member id 로 member를 찾아 올 수 있는 메소드가 필요하다
		//구글 로그인해서 찾아온 정보의 id로 이미 회원가입이 되어 있는 사람을 찾아온다.찾아 온 member 그대로 쏜다.
		Member memberVali = repo.checkId(member.getMember_id());
		if (memberVali == null) {
			//만약 같은 아이디의 회원이 없다면 가입시킨다.
			member.setMember_address("google");
			member.setMember_phone(0);
			member.setMemberImage_saveAddress("none");
			int result = repo.memberJoin(member);
			System.out.println("Google 로그인한 사람 회원 등록 완료" + result);
		}
		
    	return memberVali.getMember_id();
    }
	
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
	
	//회원정보 페이지로 이동
	@RequestMapping(value = "/memberInfo", method = RequestMethod.GET)
	public String memberInfo() {
		return "member/memberInfo";
	}
	
	
	@RequestMapping(value = "/basicChatRoom", method = RequestMethod.GET)
	public String basicChatRoom() {
		return "websocket/basicChatRoom";
	}
	
	@RequestMapping(value = "/multiChatRoom", method = RequestMethod.GET)
	public String multiChatRoom(int chatRoom_num, String chatRoom_name, Model model) {
			ChatRoom cr = new ChatRoom();
			cr.setChatRoom_name("임시방이름");
			cr.setChatRoom_num(chatRoom_num);
				System.out.println(chatRoom_num);
				int result =repo.insertRoomnum(cr);
		model.addAttribute("chatRoom_num",chatRoom_num);
		

		return "websocket/multiChatRoom";
	}

}
