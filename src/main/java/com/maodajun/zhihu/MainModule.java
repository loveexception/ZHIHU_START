package com.maodajun.zhihu;

import com.maodajun.zhihu.bean.User;
import com.maodajun.zhihu.module.UserModule;
import com.maodajun.zhihu.service.UserService;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.nutz.mvc.annotation.SetupBy;

@SetupBy(MainSetup.class)
public class MainModule {

    public static void main(String[]  arg){
        MainSetup setup = new MainSetup();


    }


}



