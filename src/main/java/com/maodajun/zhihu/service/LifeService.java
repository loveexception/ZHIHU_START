package com.maodajun.zhihu.service;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class LifeService {
    @Inject
    UserService userService;


}
