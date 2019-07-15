package com.maodajun.zhihu.module;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.maodajun.zhihu.bean.Pageing;
import com.maodajun.zhihu.service.UserService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.maodajun.zhihu.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@IocBean
public class UserModule {

    @Inject
    Dao dao;
    @Inject
    UserService userService;



    public void start() {

        Cnd cnd = Cnd.NEW();
        cnd.and("status","=","0");
        //ExecutorService service = Executors.newFixedThreadPool(20);
        while (true){
            Pager pager = new Pager();
            pager.setPageNumber(1);
            pager.setPageSize(1);
            User user = dao.query(User.class,cnd,pager).get(0);
                String token = user.getUrl_token();
              //  service.execute(new Runnable() {
//                    @Override
//                    public void run() {
                        System.out.println(token);
                        userService.doFollowing(token);
//                    }
//                });



        }


    }

    public void check() {


        while (true){
            User user = dao.query(User.class
                    ,Cnd.NEW().and("status","=","1")
                    ,new Pager(1,1))
                    .get(0);

            String token = user.getUrl_token();
            userService.check(token);




        }
    }
}
