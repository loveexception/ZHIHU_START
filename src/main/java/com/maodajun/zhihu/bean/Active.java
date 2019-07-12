package com.maodajun.zhihu.bean;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_active_start")
@Data
public class Active {

    @Id
    public int id;

    @Column
    public String token;

    @Column
    public long created_time;

    @Column
    public String verb;
    @Column
    public String action_text;
    @Column
    public String title;
    @Column
    public String author;
    @Column
    public String type;
    @Column
    public String name;


}
