package com.maodajun.zhihu.bean;

import javafx.scene.input.DataFormat;
import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.nutz.castor.Castors;

import java.util.*;

@Data
public class Life {
     User user;
    Map<String, YearMoonTools> moons= new TreeMap();
    List<String> urls =new  ArrayList<String>();
    List<String> LIFE = new ArrayList();

    public void init(){
        long start = Castors.me().castTo("2015-01-01", Calendar.class).getTimeInMillis();
        Calendar calendar = Castors.me().castTo("2017-01-01", Calendar.class);

        for (calendar.set(Calendar.DAY_OF_MONTH,1);
             calendar.getTimeInMillis() >=  start ;
             calendar.add(Calendar.MONTH,-6)) {
            LIFE.add(DateFormatUtils.ISO_DATE_FORMAT.format(calendar));
        }
        LIFE.add("2014-01-01");
        LIFE.add("2013-01-01");


    }


}
