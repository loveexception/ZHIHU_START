package com.maodajun.zhihu.bean;

import com.maodajun.zhihu.service.HttpTools;
import org.junit.Test;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;

import java.io.IOException;

import static org.junit.Assert.*;

public class HttpToolsTest {

    @Test
    public void urlToJson() throws IOException {
        HttpTools tools = new HttpTools();
//        Response res = Http.get("https://www.zhihu.com/question/49296232");
//        System.out.println( res.getContent());
        Object obj = tools.urlToJson("https://www.zhihu.com/api/v4/members/1005gel/followees?offset=0&limit=20");
        System.out.println(Json.toJson(obj));
        if(obj==null){
            urlToJson();
        }
    }

    @Test
    public void proxy() {
        HttpTools tools = new HttpTools();
        System.out.println(tools.proxy());

    }
}