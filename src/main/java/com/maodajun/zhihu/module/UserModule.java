package com.maodajun.zhihu.module;

import com.maodajun.zhihu.bean.UserToken;
import com.maodajun.zhihu.service.UserService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.maodajun.zhihu.bean.User;


@IocBean
public class UserModule {

    @Inject
    Dao dao;
    @Inject
    UserService userService;



    public void start() {

        Cnd cnd = Cnd.NEW();
        cnd.and("status","=","0");
        cnd.limit(1,1);
        while (true){

            User user = dao.query(User.class,cnd).get(0);
            String token = user.getUrl_token();

            userService.doFollowing(token);



        }


    }


}
