package com.maodajun.zhihu.module;


import com.maodajun.zhihu.bean.UserMoon;
import com.maodajun.zhihu.bean.UserToken;
import com.maodajun.zhihu.bean.UserView;
import com.maodajun.zhihu.service.UserService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.List;

@IocBean
public class ActiveModule {

    @Inject
    Dao dao;
    @Inject
    UserService userService;


    public void send() {

        while (true){


            List<UserView> users = dao.query(UserView.class,
                    null,new Pager(1,20) )
                    ;
            System.out.println(users);
            for(UserView user : users){
                try {


                    System.out.println(user.getToken());
                    userService.active(user.getToken());
                    System.out.println();





                }catch (Exception e ){
                    UserToken userToken = new UserToken();
                    userToken.setToken(user.getToken());
                    userToken.setStatus("-5");
                    dao.update(userToken);
                }


            }





        }
    }

}
