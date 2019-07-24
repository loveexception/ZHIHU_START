package com.maodajun.zhihu.bean;

import com.maodajun.zhihu.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.Assert.*;

public class LifeTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getLIFT() {
        Life life = new Life();
        life.init();;
        assertEquals(100 ,life.LIFE.size());
    }
    @Test
    public void oldManFlollowingPage() {
        UserService service = new UserService();
        YearMoonTools yearMoonTools = service.followingPage(Mock.SHEN_FOLLOWING);//Mock.SHEN_FOLLOWING
        assertEquals(yearMoonTools.getTotal(),973);
        assertEquals(yearMoonTools.getNextpageurl().length(),63);
    }



    @Test
    public void old2Man() {
        UserService service = new UserService();
        List<User> users = service.followingList(Mock.SHEN_FOLLOWING);//Mock.SHEN_FOLLOWING
        assertEquals(users.size(),20);
    }

    @Test
    public void oldManActivePage() {
        UserService service = new UserService();
        List<Active> actives = service.activeList(Mock.SHEN_ACTIVE);//Mock.SHEN_FOLLOWING
        assertEquals(actives.size(),5);
        System.out.println(Json.toJson(actives.get(0)));

    }
    @Test
    public void test(){
        String url = "https://www.zhihu.com/api/v4/members/1005gel/followees?offset=0&limit=20&session_id=1035818844434194432";


        Response response = Http.get(url);
        
        System.out.println(response.getContent());
    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        // Optional, if not specified, WebDriver will search your path for chromedriver.
        System.setProperty("webdriver.chrome.driver", "/Users/maodajun/Documents/project/zhihu/start/chromedriver");


        ChromeOptions con =new ChromeOptions();

        con.addArguments();
        WebDriver driver = new ChromeDriver(con);
        driver.get("http://www.baidu.com/");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(5000);  // Let the user actually see something!
        driver.quit();
    }

}

