package com.team.sprout.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

import com.team.sprout.dao.WantedBoardRepository;
import com.team.sprout.util.PageNavigator;
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

		try {
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

			/*
			 * String startDate = "2019-01-05"; String endDate = "2019-04-05"; Long start =
			 * (sdf.parse(startDate).getTime()); Long end = (sdf.parse(endDate).getTime());
			 * 
			 * System.out.println(start); System.out.println(end);
			 * System.out.println("3개월 long을 계산한 차이   :    " + (end - start)); //7776000000가
			 * 결과입니다.
			 */

			/*
			 * int listNumber = 0; //List는 이어져있는 html을 기준으로 찾아서 하나씩 담긴다. //List는 /로 끊긴 html을
			 * 기준으로 한개씩 담긴다. System.out.println("여기서부턴 titles의 내용입니다."); for (WebElement
			 * webElement : titles) { titlesTexts.add(webElement.getText());
			 * System.out.println(webElement.getText() +"    " + listNumber); listNumber++;
			 * }
			 * 
			 * System.out.println("여기서부턴 dates의 내용입니다."); listNumber = 0; for (WebElement
			 * webElement : dates) { System.out.println(webElement.getText() +"    " +
			 * listNumber); listNumber++; }
			 */

			// System.out.println(driver.getPageSource());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}

		List<WantedBoard> wbList = wbRepo.selectAll_crawlling(searchItem, searchWord, navi.getStartRecord(),
				navi.getCountPerPage());
		// 검증이 끝났으면 새로 모든 데이터를 불러온다.

		// board 전체 게시글 가져오기

		System.out.println("현재 요청한 페이지 : " + navi.getCurrentPage());
		System.out.println("현재 그룹 : " + navi.getCurrentGroup());
		System.out.println("현재 그룹 첫번재 페이지 : " + navi.getStartPageGroup());
		System.out.println("현재 그룹 마지막 페이지 : " + navi.getEndPageGroup());

		model.addAttribute("boardList", wbList);
		model.addAttribute("navi", navi);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("searchItem", searchItem);

		return "wantedBoard/wantedBoard";
	}

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
	
	
}
