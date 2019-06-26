package com.maodajun.zhihu.service;

import com.maodajun.zhihu.MainLauncher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;
import org.nutz.boot.NbApp;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.IocLoader;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.lang.Lang;
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


    public void init() {
        System.out.println("say mao hi");
    }




    @Test
    public void activeUrl() {
        System.out.println(service.active("shen",new Date().getTime()));
    }

    @Test
    public void followUrl() {
        System.out.println(service.following("shen",10));
    }

    @Test
    public void userUrl() {
        System.out.println(service.getUserBytoken("shen"));

    }

    //@Test
    public void getUserBytoken() {
    }

    //@Test
    public void following() {
    }

    //@Test
    public void oldMan() {
    }

    public static NbApp createNbApp() {
        return new NbApp().setMainClass(UserService.class).setPrintProcDoc(false);
    }
}