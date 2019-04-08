package com.team.sprout.controllers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WantedBoardController {

	//WebDriver
    private WebDriver driver;
    
    //Properties
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:/Users/Administrator/Dropbox/Utilities/selenium chrome driver/chromedriver.exe";
    
    //크롤링 할 URL
    private String base_url;
    
    @RequestMapping(value = "/wantedBoard", method = RequestMethod.GET)
	public String wantedBoard(Model model
			/*,@RequestParam(value = "searchItem", defaultValue = "title") String searchItem, // 만약 변수가 없다면default값으로채워진다.
			@RequestParam(value = "searchWord", defaultValue = "") String searchWord,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage // "1"이 int변수면 알아서 파싱됨.) 
*/	){
    	//System Property SetUp
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        
        //Driver SetUp
        driver = new ChromeDriver();
        base_url = "https://www.naver.com";
        
        try {
            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
            driver.get(base_url);
            System.out.println(driver.getPageSource());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
        //크롤링
        
      /*  int totalBoardCount = repo.totalBoardCount(searchItem, searchWord); // 총 게시글 수 가져오기
		PageNavigator navi = new PageNavigator(currentPage, totalBoardCount);
		// board 전체 게시글 가져오기
		List<Board> boardList = repo.boardList(searchItem, searchWord, navi.getStartRecord(), navi.getCountPerPage());

		System.out.println("현재 요청한 페이지 : " + navi.getCurrentPage());
		System.out.println("현재 그룹 : " + navi.getCurrentGroup());
		System.out.println("현재 그룹 첫번재 페이지 : " + navi.getStartPageGroup());
		System.out.println("현재 그룹 마지막 페이지 : " + navi.getEndPageGroup());
        
		model.addAttribute("boardList", boardList);
		model.addAttribute("navi", navi);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("searchItem", searchItem);*/
       
        return "wantedBoard/wantedBoard";
    }
    
    
	
	
}
