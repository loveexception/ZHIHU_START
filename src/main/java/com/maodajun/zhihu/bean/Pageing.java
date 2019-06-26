package com.maodajun.zhihu.bean;

import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
public class Pageing {
    int total;
    boolean isStart;
    boolean isEnd;
    String urltemp;
    String nextpageurl;

    long lastTime;

    public Calendar getYearMonthEnd(int year,int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month-1);
        cal = clearCalendar(cal);
        cal.add(Calendar.MONTH,1);
        cal.add(Calendar.SECOND,-1);
        return cal;
    }

    public Calendar clearCalendar(Calendar cal) {
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
    public Calendar getYearMonthStart(int year,int month){
        if(year<1970){
            year = Calendar.getInstance().get(Calendar.YEAR);
        }
        if(month <=0 | month >=13 ){
            month = Calendar.getInstance().get(Calendar.MONTH)+1;
        }
        Calendar cal =  Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month-1);
        cal = clearCalendar(cal);
        return cal;
    }

    public Calendar getYearStart(String years){
        int year = Integer.parseInt(years);
        int month = 1;

        return getYearMonthStart(year ,month);
    }
    public Calendar getYearEnd(String years){
        int year = Integer.parseInt(years);
        return getYearMonthEnd(year,12);
    }


}
