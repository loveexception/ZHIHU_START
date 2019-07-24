package com.maodajun.zhihu.bean;


import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_user_token")
@Data
public class UserToken {
    @Name
    private  String token;

    @Column
    private String status;
}
