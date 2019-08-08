package com.maodajun.zhihu.module;

import com.maodajun.zhihu.bean.UserMoon;
import com.maodajun.zhihu.service.HttpTools;
import com.maodajun.zhihu.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;

import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.json.Json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//@RunWith(NbJUnit4Runner.class)
//@IocBean(create = "init")
public class CheckModuleTest extends Assert {
    @Inject
    CheckModule module;

    @Before
    public void init() {

        module = new CheckModule();
        Ioc ioc = new NutIoc(new JsonLoader("dao.js"));
        module.dao = ioc.get(Dao.class);
        module.userService = new UserService();
        module.userService.dao = ioc.get(Dao.class);
        module.userService.httpTools = new HttpTools();
        PropertiesProxy pp= new PropertiesProxy("./"); // 文件夹,具体文件, 均可

        module.userService.conf = pp;
    }


    @Test
    public void check() {
        //module.check();



    }

    @Test
    public void moon() throws IOException {

        List<UserMoon> moons =  module.moonByToken("ai-shi-li-san-san");
        System.out.println(Json.toJson(moons));
        assertNotNull(moons);

    }

    @Test
    public void moons_bug_same_time() throws IOException {

        UserMoon moons =  module.moons("ai-shi-li-san-san","2017-01-01",new ArrayList<>());
        System.out.println(Json.toJson(moons));
        moons =  module.moons("ai-shi-li-san-san","2015-01-01",new ArrayList<>());
        System.out.println(Json.toJson(moons));
        assertNotNull(moons);

    }
//    public static NbApp createNbApp() {
//        return new NbApp().setMainClass(UserService.class).setPrintProcDoc(false);
//    }


}