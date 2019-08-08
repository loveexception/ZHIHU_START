package com.maodajun.zhihu.bean;

import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
public class YearMoonTools {
    int total;
    boolean isStart;
    boolean isEnd;

    String nextpageurl;
    String prevtopageurl;


    public Long getOffset(){
       String[] array =nextpageurl.split( "offset=");
       if(array!=null&&array[1]!=null&&Strings.isNumber(array[1])){
           return Lang.str2number(array[1]).longValue();
        }
        return null;
    }

    public Long getLastTime(){
        String[] array =nextpageurl.split( "after_id=");
        if(array!=null&&array[1]!=null){
            String[] time  = array[1].split("&");
            if(time!=null&&time[0]!=null&&Strings.isNumber(time[0])) {
                return Lang.str2number(time[0]).longValue();
            }
        }
        return null;
    }
    public Long getOlderNext(String str){
        if(Strings.equals("offset",str)){
            return getOffset();
        }
        if(Strings.equals("active",str)){
            return getLastTime();
        }
        return 0l;
    }
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


    public Long getNewPrev(String active) {
        return getFirstTime();
    }
    public Long getFirstTime(){
        String[] array =prevtopageurl.split( "before_id=");
        if(array!=null&&array[1]!=null){
            String[] time  = array[1].split("&");
            if(time!=null&&time[0]!=null&&Strings.isNumber(time[0])) {
                return Lang.str2number(time[0]).longValue();
            }
        }
        return null;
    }
}
