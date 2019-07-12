package com.maodajun.zhihu.service;

import com.maodajun.zhihu.MainLauncher;
import com.maodajun.zhihu.bean.Pageing;
import com.maodajun.zhihu.bean.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;
import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.http.Http;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.IocLoader;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.util.NutMap;
import org.nutz.mapl.Mapl;
import org.nutz.mock.NutTestRunner;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.boot.test.junit4.NbJUnit4Runner;

import javax.jws.soap.SOAPBinding;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(NbJUnit4Runner.class)
@IocBean(create = "init")
public class UserServiceTest  extends Assert {



    // 测试注入
    @Inject
    UserService service ;

    @Inject
    Ioc ioc;

    @Inject
    Dao dao ;


    public void init() {
        System.out.println("say mao hi");
    }




    @Test
    public void activeUrl() {
        Object obj =service.active("shen",new Date().getTime());
        System.out.println(Json.toJson(obj));
    }

    @Test
    public void followUrl() {

        System.out.println(service.following("shen",10));
    }

    @Test
    public void userUrl() {
        System.out.println(service.getUserBytoken("shen"));

    }

    @Test
    public void getUserBytoken() {
        Object obj = service.getUserBytoken("shen");

        NutMap map  = Lang.obj2nutmap(obj);


        User user = Lang.map2Object(map,User.class);

        assertNotNull(user);
        assertEquals(user.getUrl_token(),"shen");

        System.out.println(Json.toJson(user));
    }

    @Test
    public void following() {
        Object obj = service.following("shen",1);
        System.out.println(Json.toJson(obj));
    }

    @Test
    public void followingTrue() {
        dao.create(User.class,true);
        User user = new User();
        user.setUrl_token("shen");
        dao.insert(user);
        service.doFollowing("shen");
        int count = dao.count(User.class);
        System.out.println(count);
    }




    public static NbApp createNbApp() {
        return new NbApp().setMainClass(UserService.class).setPrintProcDoc(false);
    }
}
