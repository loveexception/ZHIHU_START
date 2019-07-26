package com.maodajun.zhihu.bean;

import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class UserMoonTest {

    @Test
    public void setIs_end() {
        Life life = new Life();
        life.init();
        UserMoon moon = new UserMoon();
        moon.setFirst("1532391817");
        moon.setLast("1533877367");
        String next =moon.next(life.getLIFE());
        assertEquals("2019-04-01",next);
    }



    @Test
    public void setMoonTable() {
        Ioc ioc = new NutIoc(new JsonLoader("dao.js"));
        Dao dao = ioc.get(Dao.class);

        //dao.create(UserMoon.class,true);
    }

    @Test
    public void baidu(){

        System.setProperty("webdriver.chrome.driver", "/Users/maodajun/Documents/project/zhihu/start/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://baidu.com");

        String str = driver.getPageSource();
        System.out.println(str);
    }

}