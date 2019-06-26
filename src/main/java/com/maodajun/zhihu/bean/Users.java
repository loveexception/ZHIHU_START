package com.maodajun.zhihu.bean;

import com.maodajun.zhihu.service.UserService;
import lombok.Data;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Iterator;
import java.util.List;

@IocBean
@Data
public class Users implements Iterator<User> {
    Pageing pageing;
    List<User> users;

    @Inject
    UserService service;


    @Override
    public boolean hasNext() {

        return false;
    }

    @Override
    public User next() {
        return null;
    }
}
