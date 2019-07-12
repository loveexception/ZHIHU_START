package com.maodajun.zhihu;

import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class MainSetup implements Setup {
    public static Ioc ioc;

    public void init(NutConfig conf) {

        MainSetup.ioc = conf.getIoc();
    }

    @Override
    public void destroy(NutConfig nc) {

    }
}


