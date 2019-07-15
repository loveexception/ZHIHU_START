package com.maodajun.zhihu.bean;

import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.nutz.castor.Castors;

import java.util.*;

@Data
public class Life {
     User user;
    Map<String,Pageing> moons= new TreeMap();
    List<String> urls =new  ArrayList<String>();
    List<String> LIFE = new ArrayList();

    public void init(){
        long start = Castors.me().castTo("2011-01-01", Calendar.class).getTimeInMillis();
        Calendar calendar = Castors.me().castTo("2019-05-01", Calendar.class);

        for (calendar.set(Calendar.DAY_OF_MONTH,1);
             calendar.getTimeInMillis() >  start ;
             calendar.add(Calendar.MONTH,-6)) {
            LIFE.add(DateFormatUtils.ISO_DATE_FORMAT.format(calendar));
        }


    }


}
