package com.maodajun.zhihu.module;

import com.maodajun.zhihu.bean.*;
import com.maodajun.zhihu.service.HttpTools;
import com.maodajun.zhihu.service.UserService;
import org.nutz.castor.Castors;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;

import java.io.IOException;
import java.util.*;


@IocBean
public class CheckModule {

    @Inject
    Dao dao;
    @Inject
    UserService userService;



    public void check() {
        int i = 1;
        while (true){


            List <UserToken> users = dao.query(UserToken.class
                    ,Cnd.wrap("status is null")
                    ,new Pager(i++,10))
                    ;

            for (int j = 0; j <users.size(); j++) {

                UserToken token = users.get(j);
                try {

                    List<UserMoon> moon = moonByToken(token.getToken());
                    token.setStatus("0");
                    dao.update(token);
                    dao.insert(moon);


                }catch (Exception e){
                    token.setStatus("-2");
                    dao.update(token);

                }
            }



        }
    }

    public List<UserMoon> moonByToken(String token) throws IOException {

        Life life = new Life();
        life.init();
        List<String> times = life.getLIFE();
        List<UserMoon> moons = new LinkedList<UserMoon>();
        for (int i = 0; i < times.size(); i++) {
            String time = times.get(i);
            UserMoon moon = moons(token, time, moons);
            if(Lang.isNotEmpty(moon)){
                moons.add(moon);

            }

            if(Lang.equals("false",moon.getIs_end())){
                continue;
            }else{
                return moons;

            }

        }



        return moons;
    }
    HttpTools httpTools = new  HttpTools();
    public UserMoon moons(String token,String time,  List<UserMoon> moons ) throws IOException {
        
        long tt = Castors.me().castTo(time, Calendar.class).getTimeInMillis();
        String url = userService.activeUrl(token,tt);
        System.out.println(url);
        Object obj = httpTools.urlToJson2(url);
        System.out.println(obj);


        YearMoonTools page = userService.activePage(Json.toJson(obj));
        List<Active>  actives = userService.activeList(Json.toJson(obj));


        if(Lang.isEmpty(actives)){
            return null;
        }
        Active last  = actives.get(0);
        Active first = actives.get(actives.size()-1);

        UserMoon moon = new UserMoon();
        moon.setToken(token);
        moon.setMoon(time);
        moon.setIs_end(""+page.isEnd());
        moon.setFirst(first.getCreated_time()+"");
        moon.setLast(last.getCreated_time()+"");
        return moon;
    }
    private UserMoon moonsHttp(String token,String time,  List<UserMoon> moons ) {

        long tt = Castors.me().castTo(time, Calendar.class).getTimeInMillis();
       // Object  obj = userService.active(token,tt);

        String url = userService.activeUrl(token,tt);
        Response res = Http.get(url);
        String body = res.getContent();
        if(body.indexOf("请输入验证码进行验证")>0){
            setProxy();
        }
        YearMoonTools page = userService.activePage(body);
        List<Active>  actives = userService.activeList(body);
        UserMoon moon = new UserMoon();
        moon.setToken(token);
        moon.setMoon(time);
        if(Lang.isEmpty(actives)){
            return null;
        }
        Active last  = actives.get(0);
        Active first = actives.get(actives.size()-1);
        moon.setIs_end(""+page.isEnd());

        moon.setFirst(first.getCreated_time()+"");
        moon.setLast(last.getCreated_time()+"");
        return moon;
    }

    private void setProxy() {
        Response response = Http.get("http://localhost:5010/get/");

        String temp =response.getContent();
        String[] array = temp.split(":");
        String host = array[0];
        int port = Lang.str2number(array[1]).intValue();



    }

}
