package com.maodajun.zhihu.module;

import com.maodajun.zhihu.service.HttpTools;
import com.maodajun.zhihu.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.lang.util.NutMap;

import java.io.IOException;

import static org.junit.Assert.*;

public class MyModuleTest {
    MyModule module = new MyModule();
    UserService userService = new UserService();

    @Before
    public void init(){
        module.userService = userService;
        Ioc ioc = new NutIoc(new JsonLoader("dao.js"));
        module.dao = ioc.get(Dao.class);
        module.userService = new UserService();
        module.userService.dao = ioc.get(Dao.class);
        module.userService.httpTools = new HttpTools();
        PropertiesProxy pp= new PropertiesProxy("./"); // 文件夹,具体文件, 均可

        module.userService.conf = pp;    }

    @Test
    public void checkOne1() throws IOException {
        module.moons = NutMap.NEW().addv("start","20190101").addv("end","20200101");
        Object obj;
        obj = module.checkOne("funjichong-gua-ji-di");
        assertNull(obj);




    }

    @Test
    public void checkOne2() throws IOException {
        module.moons = NutMap.NEW().addv("start","20080101").addv("end","20150101");
        Object obj;

        obj = module.checkOne("shen");
        assertNotNull(obj);



    }
}