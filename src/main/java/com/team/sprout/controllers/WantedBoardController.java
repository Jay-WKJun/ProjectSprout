package com.team.sprout.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.team.sprout.dao.WantedBoardRepository;
import com.team.sprout.util.FileService;
import com.team.sprout.util.PageNavigator;
import com.team.sprout.util.profileFile;
import com.team.sprout.vo.WantedBoard;

@Controller
public class WantedBoardController {

	@Autowired
	WantedBoardRepository wbRepo;

	// WebDriver
	private WebDriver driver;

	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/chromeDriver/chromedriver.exe";
	public static final String PHANTOM_DRIVER_PATH = "C:/phantomjs/bin/phantomjs.exe";

	// 크롤링 할 URL
	private String base_url;

	@RequestMapping(value = "/wantedBoard", method = RequestMethod.GET)
	public String wantedBoard(Model model,
			@RequestParam(value = "searchItem", defaultValue = "WANTEDBOARD_TITLE") String searchItem // 만약 변수가
																										// 없다면default값으로채워진다.
			, @RequestParam(value = "searchWord", defaultValue = "") String searchWord,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage // "1"이 int변수면 알아서 파싱됨.)
	) {
		int totalBoardCount = wbRepo.totalBoardCount(searchItem, searchWord); // 총 게시글 수 가져오기
		PageNavigator navi = new PageNavigator(currentPage, totalBoardCount);

		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");

		// Driver SetUp
		driver = new ChromeDriver(options);
		//driver = testphantomjs();
		base_url = "http://www.nipa.kr/main.it";
		// !!이 로딩은 처음에만 해준다. static으로 true를 false로 바꿔줘서 해준다.
		// 크롤링 긁어오는 거는 새로운 클래스에 메소드를 하나씩 추가해서 사용한다.(검증작업까지 시켜준다.)

		/*try {
			// get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(base_url);

			WebElement we = driver.findElement(By.className("not02_color"));
			we.click();

			String pageTitle = "정보통신산업진흥원";
			// 이 타이틀은 그냥 수기로 입력합시다 ㅇㅇ

			List<WebElement> titles = driver.findElements(By.className("title"));
			// 제목을 뽑아내기
			List<WebElement> dates = driver.findElements(By.className("date"));
			// 날짜 뽑아내기

			// 가져온것을 wb객체에 넣고 list정렬
			List<WantedBoard> wbListTemp = new ArrayList<>();
			for (int i = 0; i < titles.size(); i++) {
				WantedBoard wb = new WantedBoard();
				wb.setWantedBoard_title(titles.get(i).getText());
				wb.setWantedBoard_content(titles.get(i).getAttribute("onclick"));
				wb.setWantedBoard_date(dates.get(i).getText());
				wb.setWantedBoard_from(pageTitle);
				wbListTemp.add(wb);
			}

			for (WantedBoard wantedBoard : wbListTemp) {
				System.out.println(wantedBoard.toString());
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 오늘 날짜
			Calendar c1 = Calendar.getInstance();
			Long todayLong = c1.getTimeInMillis();
			Long threeMonths = 7776000000L;

			for (WantedBoard wantedBoard : wbListTemp) {
				if ((todayLong - sdf.parse(wantedBoard.getWantedBoard_date()).getTime()) >= threeMonths) {
					// 이 경우엔 아무것도 안하거나 있으면 지운다.
					System.out.println("3개월 이상의 메소드로 들어옴");
					System.out.println(wantedBoard.getWantedBoard_title() + "   3개월 이상입니다...");
					if (wbRepo.selectOneBoard(wantedBoard.getWantedBoard_title()) != null) {
						System.out.println("지우는 메소드로 드어옴");

						System.out.println("3개월 이상이므로 지웁니다.");
						wbRepo.deleteBoard(wantedBoard.getWantedBoard_title());
					}
				} else {
					// 3개월 이내의 데이터라면...
					System.out.println("else메소드로 들어옴");
					if (wbRepo.selectOneBoard(wantedBoard.getWantedBoard_title()) == null) {
						// title로 select했는데 없다면 추가한다.
						// TODO:applyBoard num이 연결되있지 않음, content 안넣음(링크정보넣기)
						System.out.println("추가하는 메소드로 들어옴");
						wantedBoard.setApplyBoard_num(0);
						wantedBoard.setWantedBoard_hitCount(0);
						wantedBoard.setWantedBoard_content("content입니다.");

						wbRepo.insertBoard(wantedBoard);
					}
				}
			} // 여기까지 검증완료

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}*/

		List<WantedBoard> wbList = wbRepo.selectAll_crawlling(searchItem, searchWord, navi.getStartRecord(),
				navi.getCountPerPage());

		System.out.println("현재 요청한 페이지 : " + navi.getCurrentPage());
		System.out.println("현재 그룹 : " + navi.getCurrentGroup());
		System.out.println("현재 그룹 첫번재 페이지 : " + navi.getStartPageGroup());
		System.out.println("현재 그룹 마지막 페이지 : " + navi.getEndPageGroup());

		model.addAttribute("boardList", wbList);
		model.addAttribute("navi", navi);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("searchItem", searchItem);
		
		//--------------------------------------------------------- 내부 공고글 불러오기 삭제 보류하기 (환.)
//		List<WantedBoard> list_from_DB = wbRepo.selectAll_DB();
//		model.addAttribute("list_from_DB", list_from_DB);
		//---------------------------------------------------------
		
		return "wantedBoard/wantedBoard";
	}
	
	/* 내부에서 작성한 공고글. */
	@RequestMapping(value = "/internal", method = RequestMethod.GET)
	public String internal(Model model) {
		List<WantedBoard> list_from_DB = wbRepo.selectAll_DB();
		model.addAttribute("list_from_DB", list_from_DB);
		
		return "wantedBoard/internal_wantedBoard";
	}
	
	/* ??? */
	@RequestMapping(value = "/wantedBoardLink", method = RequestMethod.GET)
	public String wantedBoardLink(String link) {

		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		// Driver SetUp
		driver = new ChromeDriver();
		base_url = "http://www.nipa.kr/main.it";
		driver.get(base_url);
		WebElement we = driver.findElement(By.className("not02_color"));
		we.click();
		WebElement gogogo = driver.findElement(By.linkText(link));
		gogogo.click();

		return null;
	}

	/* 페이지 이동. */
	@RequestMapping(value = "/boardRegist", method = RequestMethod.GET) //--------------------- Whan's job
	public String boardRegist(Model model, HttpSession session) {
		System.out.println("내부 공고 글 작성하기");
		String loginId = (String) session.getAttribute("loginId");
		model.addAttribute("loginId", loginId);
		return "wantedBoard/upload_wanted_board";
	}

	/* 글 작성하기. */
	@RequestMapping(value = "/write_wanted", method = RequestMethod.GET)
	public String boardRegist(WantedBoard board, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");

			board.setWantedBoard_from(loginId);
			board.setWantedboard_source(1);// 직접 작성한 글이라 1이다.( 크롤링은 0 )
			board.setWantedBoard_hitCount(0);

			wbRepo.insertBoard_directly(board);

			return "redirect:/"; 
		
	} // detail_info
	
	@RequestMapping(value = "/detail_info", method = RequestMethod.GET) //--------------------- this place
	public String detail_info(HttpSession session, Model model, String wantedBoard_num) {
		WantedBoard one_wanted_from_DB = wbRepo.selectOneBoard_by_id(wantedBoard_num);

		String loginId = ((String) session.getAttribute("loginId")).trim();
		String wanted_id = one_wanted_from_DB.getWantedBoard_from().trim();
		String same;
		
		// 공고글 올린 사람과  == 로그인한 사람 ?? check 하기
		if (loginId.equals(wanted_id)) { 		// 같은 > 삭제하기(null)
			same = null;
			session.setAttribute("same", same);
		} else {								// 다름 > 지원하기(not null)
			same = "same";
			session.setAttribute("same", same);
		}
		model.addAttribute("one_wanted_from_DB", one_wanted_from_DB);
		return "wantedBoard/detail_info";
	}
	
	/* 직접 올린 공고글 보기 */
	@RequestMapping(value = "/boardList", method = RequestMethod.GET) //--------------------- this place
	public String boardList() {
		return "redirect:/wantedBoard";
	}
	
	/* 직접 올린 공고글 삭제하기 */
	@RequestMapping(value = "/delete_wanted", method = RequestMethod.GET) //--------------------- this place
	public String delete_wanted(String wantedBoard_num) {
		wbRepo.deleteBoard_by_num(wantedBoard_num);
		return "redirect:/";
	}

	/* 공고지원 페이지로 이동 */
	@RequestMapping(value = "/apply_wanted", method = RequestMethod.GET) //--------------------- this place
	public String apply_wanted(HttpSession session, Model model) {
		String loginId = ((String) session.getAttribute("loginId")).trim();
		model.addAttribute("loginId", loginId);
		return "wantedBoard/apply_wanted";
	}
	
	/* 공고에 지원하기 */
	@RequestMapping(value = "/apply_this_job", method = RequestMethod.POST) //--------------------- this place
	public String apply_this_job(WantedBoard wanb, MultipartFile upload) {
		FileService fileserv = new FileService();
		
		if (upload.isEmpty() == false) {
			fileserv.saveFile(upload, wanb); 
		} else {
			wanb.setWantedBoard_title("지원글");
		}
		
		// DB에서 error 안나게 default로 setting 하기
		wanb.setApplyBoard_num(0);
		wanb.setWantedBoard_hitCount(0);
		wanb.setWantedboard_source(2);
		
		/*System.out.println();
		System.out.println("for check >>>> " + wanb.toString());
		System.out.println("for check >>>> " + upload.getOriginalFilename());
		System.out.println();*/
		
		int seq = wbRepo.insertBoard_directly(wanb);
		wanb.setWantedBoard_num(seq);
		wanb.setApplyBoard_num(seq);
		
		System.out.println("--------------------------------------------------------------");
		System.out.println("최종적으로 잡힌 지원글 >>" + wanb.toString());
		System.out.println("--------------------------------------------------------------");
		
		wbRepo.update_ApplyBorad(wanb);     // 여기하다 밥 먹으로 감.
		
		return "redirect:/";
	}
}
