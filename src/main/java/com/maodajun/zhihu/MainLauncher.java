package com.maodajun.zhihu;

import com.maodajun.zhihu.module.CheckModule;
import com.maodajun.zhihu.module.UserModule;
import org.nutz.boot.NbApp;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.*;
import org.nutz.mvc.annotation.*;
import org.nutz.dao.Dao;

@IocBean(create="init", depose="depose")
public class MainLauncher {
    
    @Inject
    protected PropertiesProxy conf;
    @Inject
    protected Dao dao;

    @Inject
    private  UserModule userModule;
    @Inject
    private CheckModule checkModule;

    @At("/")
    @Ok("->:/index.html")
    public void index() {}
    
    public void init() {
        // NB自身初始化完成后会调用这个方法
        //dao.create(User.class, false);
        //dao.create(Active.class,false);

        new Thread(()-> checkModule.check()).start();
        new Thread(()-> userModule.start()).start();












    }
    public void depose() {}

    public static void main(String[] args) throws Exception {
        //new NbApp().setArgs(args).setPrintProcDoc(true).run();
        NbApp app =new NbApp();
        app.run();

    }

}
