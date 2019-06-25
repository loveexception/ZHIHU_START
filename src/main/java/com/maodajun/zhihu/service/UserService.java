package com.maodajun.zhihu.service;

import com.maodajun.zhihu.bean.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.segment.CharSegment;
import org.nutz.lang.segment.Segment;

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
    public String followUrl (String token){
        String url = conf.get("zhihu.following");
        Segment seg = new CharSegment(url);
        return seg.set("token",token).toString();
    }
    public String userUrl (String token){
        String url = conf.get("zhihu.user");
        Segment seg = new CharSegment(url);
        return seg.set("token",token).toString();
    }


    public User getUserBytoken(String token){
        Object obj = httpTools.urlToJson(userUrl(token));


        return null;
    }

    public List<User> following(String token){
        Object obj = httpTools.urlToJson(followUrl(token));


        return null;
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
