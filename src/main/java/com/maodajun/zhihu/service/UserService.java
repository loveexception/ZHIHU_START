package com.maodajun.zhihu.service;

import com.maodajun.zhihu.bean.Active;
import com.maodajun.zhihu.bean.Life;
import com.maodajun.zhihu.bean.Pageing;
import com.maodajun.zhihu.bean.User;
import org.nutz.castor.Castors;
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
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    public String followUrl (String token,int offset){
        String url = conf.get("zhihu.following");
        Segment seg = new CharSegment(url);
        return seg.set("token",token).set("offset",offset).toString();
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

    public  Object  following(String token,int offset){
        String url =followUrl(token,offset);
        Object obj = httpTools.urlToJson(url);
        return obj;
    }
    public  Object  active(String token, long time){
        Object obj = httpTools.urlToJson(activeUrl(token,time));
        return obj;
    }



    public List<User> oldMan(List<User> newman){
        Cnd cnd =Cnd.NEW();
        for (User use: newman) {
            cnd.or("token","like",use.getUrl_token());
        }
        return dao.query(User.class,cnd);
    }


    public Pageing followingPage(String json) {
        String mock = "{" +
                "'paging.totals':'total'" +
                ",'paging.is_start':'isStart'" +
                ",'paging.is_end':'isEnd'" +
                ",'paging.next':'nextpageurl'" +

                "}";
        Object change =Json.fromJson(mock);
        Object obj =Json.fromJson(json);
        obj = Mapl.convert(obj,change);
        return Json.fromJson(Pageing.class,Json.toJson(obj));


    }

    public List<User> followingList(String json) {
        Object obj = Json.fromJson(json);
        obj = Mapl.cell(obj,"data");
        List<User> users = Json.fromJsonAsList(User.class,Json.toJson(obj));
        return users;
    }

    public Pageing activePage(String json) {
        String mock = "{" +
                ",'paging.is_end':'isEnd'" +
                ",'paging.next':'nextpageurl'" +
                "}";
        Object change =Json.fromJson(mock);
        Object obj =Json.fromJson(json);
        obj = Mapl.convert(obj,change);
        return Json.fromJson(Pageing.class,Json.toJson(obj));


    }

    public List<Active> activeList(String json) {
        Object obj = Json.fromJson(json);
        String model = "{data:[{'id':'[].id'" +
                ", 'created_time':'[].created_time' " +
                ",'verb':'[].verb'" +
                ",'action_text':'[].action_text'" +
                ",'actor.url_token':'[].token'" +
                  ",'target.title':'[].title'" +
                ",'target.type':'[].type'" +
                ",'target.author.name':'[].name'" +
                ",'target.author.url_token':'[].author'" +
                "}]" +
                "}";
        Object change =Json.fromJson(model);
        obj = Mapl.convert(obj,change);

        List<Active> actives = Json.fromJsonAsList(Active.class,Json.toJson(obj));
        return actives;
    }

    public void doFollowing(String token) {
        try {


            //1. 建立 page
            Object obj = following(token, 0);
            if (obj == null) {
                throw Lang.makeThrow("server lost",obj);

            }

            Pageing pageing = followingPage(Json.toJson(obj));
            //2. 循环查寻 end停止
            List result = followingList(Json.toJson(obj));
            if (pageing == null) {

                throw Lang.makeThrow("user lost",pageing);
            }

            System.out.println(pageing.getTotal());
            while (!pageing.isEnd()) {
                //3。 解析数据
                obj = following(token, pageing.getNext("offset").intValue());
                pageing = followingPage(Json.toJson(obj));
                List<User> temp =followingList(Json.toJson(obj));
                for (int i = 0;  i < temp.size(); i++) {
                    Object o = getUserBytoken(temp.get(i).getUrl_token());
                    User user =  Json.fromJson(User.class,Json.toJson(o));
                    result.add(user);
                }

                if (result.size() > 60) {
                    System.out.print(".");
                    //4。 一组入库
                    doTrans(result);
                    result.clear();
                }

            }
            doTrans(result);
            System.out.println(token);
            User user = dao.fetch(User.class, token);

            user.setStatus("1");
            dao.update(user);
        }catch (Exception e){
            errorUser(token, "-2");

        }finally {

        }


    }

    private List doTrans(List result) {
        Trans.exec(new Atom() {
            public void run() {
                dao.insertOrUpdate(result);



            }
        });
        return result;
    }

    private void errorUser(String token, String s) {
        User user = dao.fetch(User.class, token);

        user.setStatus(s);
        dao.update(user);
    }

    public void check(String token) {
        Life life = new  Life();
        life.init();
        List<String> keys = life.getLIFE();
        for (String key:keys) {

            Date date =  Castors.me().castTo(key, Date.class);
            long time = date.getTime();

            Object obj =  active(token,time);
            Pageing pageing =  activePage(Json.toJson(obj));

            life.getMoons().put(key,pageing);
        }
        System.out.println(life);

    }


}
