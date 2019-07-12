package com.maodajun.zhihu.bean;

import com.maodajun.zhihu.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;

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
        Pageing pageing = service.followingPage(Mock.SHEN_FOLLOWING);//Mock.SHEN_FOLLOWING
        assertEquals(pageing.getTotal(),973);
        assertEquals(pageing.getNextpageurl().length(),63);
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

}

