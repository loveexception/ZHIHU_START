package com.maodajun.zhihu.module;

import com.maodajun.zhihu.bean.*;
import com.maodajun.zhihu.service.UserService;
import org.nutz.castor.Castors;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;

import java.util.*;


@IocBean
public class CheckModule {

    @Inject
    Dao dao;
    @Inject
    UserService userService;



    public void check() {
        int i = 1;

        if (true){
            List <UserToken> users = dao.query(UserToken.class
                    ,Cnd.wrap("status is null")
                    ,new Pager(i++,100))
                    ;
            List<UserToken> result =new  ArrayList<UserToken>();

            List<UserMoon> moons = new ArrayList<UserMoon>();
            System.out.println(users);
            for (int j = 0; j <users.size(); j++) {
                UserToken token = users.get(i);

                List<UserMoon> moon = moonByToken(token.getToken());
                token.setStatus("0");
                result.add(token);
                moons.addAll(moon);

            }
            dao.update(result);
            dao.update(moons);

        }
    }

    public List<UserMoon> moonByToken(String token) {

        Life life = new Life();
        life.init();
        List<String> times = life.getLIFE();
        List<UserMoon> moons = new LinkedList<UserMoon>();
        for (int i = 0; i < times.size(); i++) {
            String time = times.get(i);
            long tt = Castors.me().castTo(time, Calendar.class).getTimeInMillis();
            Object  obj = userService.active(token,tt);
            //System.out.println(obj);
             YearMoonTools page = userService.activePage(Json.toJson(obj));
             List<Active>  actives = userService.activeList(Json.toJson(obj));
             UserMoon moon = new UserMoon();
             moon.setToken(token);
             moon.setMoon(time);
             Active last  = actives.get(0);
             Active first = actives.get(actives.size()-1);

             moon.setFirst(first.getCreated_time()+"");
             moon.setLast(last.getCreated_time()+"");
             moons.add(moon);

        }



        return moons;
    }


}
