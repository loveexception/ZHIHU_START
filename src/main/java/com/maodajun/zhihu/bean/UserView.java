package com.maodajun.zhihu.bean;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("startoneyear")
@Data
public class UserView {
    @Column
    private  String token;
    @Column
    private  String first;
    @Column
    private  String last;
    @Column
    private  String moon;
}
