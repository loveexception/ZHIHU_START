package com.maodajun.zhihu.bean;


import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.nutz.castor.Castors;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Times;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Table("t_user_moon")
public class UserMoon {

    @Id
    int id;
    @Column
    String token;
    @Column
    String moon;
    @Column
    String is_end;
    @Column
    String first;
    @Column
    String last;


    public String next(List<String> life) {
        for (String temp:life) {
            Calendar c = Castors.me().castTo(temp, Calendar.class);
            long  a = c.getTimeInMillis() - Long.parseLong(first)*1000;
            long  b = c.getTimeInMillis() - Long.parseLong(last)*1000;
            System.out.println(""
                    +"a:"+a+"\tb:"+b
                            +"\tlife:" +temp
                            +"\tfirst:"+ DateFormatUtils.ISO_DATE_FORMAT.format(Long.parseLong(first)*1000)
                            +"\tlast:"+ DateFormatUtils.ISO_DATE_FORMAT.format(Long.parseLong(last)*1000)
            );
            if(a<0&&b<0){
                return temp;
            }
        }
        return "";
    }
}
