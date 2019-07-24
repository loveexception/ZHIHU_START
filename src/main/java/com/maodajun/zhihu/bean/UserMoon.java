package com.maodajun.zhihu.bean;


import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Data
@Table("t_user_moon")
public class UserMoon {
    @Name
    String token;
    @Column
    String moon;
    @Column
    String is_end;
    @Column
    String first;
    @Column
    String last;





}
