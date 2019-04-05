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
	@RequestMapping(value = "/join", method = RequestMethod.POST) // ------------------------------------ 하는 중.
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

		int result = repo.memberJoin(member); // 회원가입
		System.out.println("회원가입 결과 : " + result); // 회원가입 결과 확인

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
	@RequestMapping(value = "/login", method = RequestMethod.POST) // ------------------------------------ 하는 중.
	public String loginP(String id, String password, boolean idChecked,
			@CookieValue(value = "idChecked", defaultValue = "") String uid, Model model, HttpSession session,
			HttpServletResponse response) {

		if (idChecked) {
			Cookie cookie = new Cookie("idChecked", id);// idCheck를 눌럿다면
			cookie.setMaxAge(24 * 60 * 60); // ()안에는 초단위 int, 24시간동안 cookie를 유지하라는 얘기
			response.addCookie(cookie); // HDD에 쿠키에 저장하라는 명령이다.
		} else { // 쿠키 삭제해야함
			Cookie cookie = new Cookie("idChecked", null);
			cookie.setMaxAge(0); // expire time = 0 = 즉시 지워라
			response.addCookie(cookie);
		}

		Map<String, String> map = new HashMap<>();

		map.put("id", id);
		map.put("password", password);

		// 입력된 정보로 DB가서 그 정보를 가져온다.
		Member m = repo.selectOne(map); // m은 DB에서 가져온 정보가 담김(김환이 가져다 씀.)

		// 로그인 성공
		String message = null;

		if (m == null || !(m.getMember_id().equals(id) && m.getMember_id().equals(id))) {
			message = "아이디 혹은 비밀번호가 맞지 않습니다.";
			model.addAttribute("message", message);
			return "member/loginForm";
		} else {
			System.out.println("로그인 성공 **********************");
			session.setAttribute("loginId", m.getMember_id());
			session.setAttribute("loginName", m.getMember_name());
			session.setAttribute("loginNum", m.getMember_num());
			// ------------------------------------------------ login 후 이미지(파일) 불러오기
			String mime = null; // mime은 사진 형식인지 확인하기 위한것..... (image/jpeg)
			String fullPath = m.getMemberImage_saveAddress(); // profile_img가 저장된 위치.

			if (m.getMemberImage_saveAddress() == null) { // 회원가입 할 때 프로필 사진을 지정안함
				System.out.println("회원가입할 때 프로필 사진 지정을 안함");
			} else {
				try {
					mime = Files.probeContentType(Paths.get(fullPath));
					mime.contains("image"); // 해당 문자열 안에 내가 원하는 문자가 포함되어 있는가
					session.setAttribute("mime", mime); // .jsp에 가져갈 이미지 파일을 담았다.
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // if & else
				// ----------------------------------------------------- 환.
		}
		return "redirect:/";
	}

	/*
	 * ID로 img파일 불러오기.
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET) // anchor tag
	public void download(String loginId, HttpServletResponse response) {
		Member m = repo.checkId(loginId);// 재사용.
		String fullPath = m.getMemberImage_saveAddress();

		FileInputStream filein = null;
		ServletOutputStream fileout = null;

		try {
			filein = new FileInputStream(fullPath);// must to checked by try & catch
			fileout = response.getOutputStream();
			FileCopyUtils.copy(filein, fileout);

			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// 이거 지우면 프로필 사진 못불러옴 (by 환.)

	/*
	 * update GET
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		System.out.println("update_get");
		System.out.println();
		String id = (String) session.getAttribute("loginId");
		String password = (String) session.getAttribute("loginPassword");

		Map<String, String> map = new HashMap<>();

		map.put("id", id);
		map.put("password", password);

		Member member = repo.selectOne(map);
		System.out.println(member.toString());

		model.addAttribute("num", member.getMember_num());
		model.addAttribute("id", member.getMember_id());
		model.addAttribute("password", member.getMember_password());
		model.addAttribute("name", member.getMember_name());
		model.addAttribute("phone", member.getMember_phone());
		model.addAttribute("address", member.getMember_address());

		return "member/updateForm";
	}

	/*
	 * update POST 실제 작동부분
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateP(Member member, HttpSession session, MultipartFile newPicture) {
		System.out.println("update_get");
		System.out.println();
		System.out.println("new"+member.toString());
		/*int result = repo.updateMember(member);
		// 확인용
		System.out.println(result);

		session.invalidate();// 세션 삭제.
*/
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
		System.out.println("---------- 회원가입 page ----------");
		System.out.println("session에 저장된 id : " + loginId);
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
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // if & else

		model.addAttribute("member", member);

		return "member/memberInfo";
	}

	/*
	 * 회원정보 수정 POST
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Member member, MultipartFile newPicture) {
		System.out.println("수정된 정보 : "+member.toString());
		
		
		return "redirect:/";
	}

}
