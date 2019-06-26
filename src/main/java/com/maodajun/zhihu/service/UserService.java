package com.maodajun.zhihu.service;

import com.maodajun.zhihu.bean.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.segment.CharSegment;
import org.nutz.lang.segment.Segment;
import org.nutz.mapl.Mapl;

import java.util.List;

@IocBean
public class UserService {
    @Inject
    Dao dao;
    @Inject
    HttpTools httpTools;
    @Inject
    protected PropertiesProxy conf;

    public String activeUrl (String token,long time ){
        String url = conf.get("zhihu.active");
        Segment seg = new CharSegment(url);
        return seg.set("token",token).set("time",time).toString();
    }
    public String followUrl (String token,int page){
        String url = conf.get("zhihu.following");
        Segment seg = new CharSegment(url);
        return seg.set("token",token).set("page",page).toString();
    }
    public String userUrl (String token){
        String url = conf.get("zhihu.user");
        Segment seg = new CharSegment(url);
        return seg.set("token",token).toString();
    }


    public Object getUserBytoken(String token){
        Object obj = httpTools.urlToJson(userUrl(token));
        return obj;
    }

    public  Object  following(String token,int page){
        Object obj = httpTools.urlToJson(followUrl(token,page));
        return obj;
    }
    public  Object  active(String token, long time){
        Object obj = httpTools.urlToJson(activeUrl(token,time));
        return obj;
    }



    public List<User> oldMan(List<User> newman){
        Cnd cnd =Cnd.NEW();
        for (User use: newman) {
            cnd.or("token","like","");
        }
        dao.query(User.class,cnd);
        return null;
    }


}
