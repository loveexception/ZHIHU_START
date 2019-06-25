package com.maodajun.zhihu.service;

import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

@IocBean
public class HttpTools {

    public Object urlToJson(String url){
        Response res = Http .get(url);
        if(res.isOK()){
            return Json.fromJson(res.getReader());
        }
        return null;
    }
}
