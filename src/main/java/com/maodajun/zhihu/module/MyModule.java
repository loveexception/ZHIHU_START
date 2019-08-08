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
import org.nutz.lang.util.NutMap;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


@IocBean
public class MyModule {

    @Inject
    Dao dao;
    @Inject
    UserService userService;



    public void check() {
        int i = 1;
        while (true){


            List <UserToken> users = dao.query(
                    UserToken.class,Cnd.wrap("status is null"),new Pager(i++,10)
            );


            for (int j = 0; j <users.size(); j++) {

                UserToken token = users.get(j);
                try {

                    User moon = checkOne(token.getToken());
                    if(Lang.isEmpty(moon)){
                        token.setStatus("-5");
                        dao.update(token);
                        continue;
                    }
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
    public NutMap moons =// NutMap.NEW().addv("start","20190101").addv("end","20200101");
    NutMap.NEW().addv("start","20140101").addv("end","20150101");
    HttpTools httpTools = new HttpTools();

    public  User checkOne(String token) throws IOException {

        long start =0; String url="";Object obj;YearMoonTools page;
         start = Castors.me().castTo(moons.getString("end"), Calendar.class).getTimeInMillis()/1000;
         url = userService.activeUrl(token,start);
         obj = httpTools.urlToJson2(url);
         page = userService.activePage(Json.toJson(obj));
         System.out.println(page);
        if(page.isEnd()){
            return null;
        }


         start = Castors.me().castTo(moons.getString("start"), Calendar.class).getTimeInMillis()/1000;
         url = userService.activeUrl(token,start);
         obj = httpTools.urlToJson2(url);

         page = userService.activePage(Json.toJson(obj));
        if(!page.isEnd()){
            return null;
        }

        url =userService.userUrl(token);
        obj = httpTools.urlToJson2(url);
        User user = Json.fromJson(User.class,Json.toJson(obj));
        if(user.hello()<300){
            return null;
        }
        System.out.println(user.hello()+"-----------"+user);

        return user;
    }


}
