package com.maodajun.zhihu.bean;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class PageingTest {

    @Test
    public void getYearMonthEndnw() {
        YearMoonTools yearMoonTools = new YearMoonTools();
        Calendar time = yearMoonTools.getYearMonthStart(1900,13);
        String str = DateFormatUtils.ISO_DATE_FORMAT.format(time);
        System.out.println(str);
        assertEquals("2019-06-01",str);

    }
    @Test
    public void getYearMonthEnd() {
        YearMoonTools yearMoonTools = new YearMoonTools();


        Calendar time = yearMoonTools.getYearMonthEnd(2018,7);
        String str = DateFormatUtils.ISO_DATE_FORMAT.format(time);
        System.out.println(str);
        assertEquals("2018-07-31",str);
    }
    @Test
    public void getYearMonthStart() {
        YearMoonTools yearMoonTools = new YearMoonTools();


        Calendar time = yearMoonTools.getYearMonthStart(2018,7);
        //assertEquals(1564588799445l,time);
        String str = DateFormatUtils.ISO_DATE_FORMAT.format(time);
        System.out.println(str);
        assertEquals("2018-07-01",str);
    }
    @Test
    public void getYearStart() {
        YearMoonTools yearMoonTools = new YearMoonTools();

        Calendar time = yearMoonTools.getYearStart("2019");
        //assertEquals(1564588799445l,time);
        String str = DateFormatUtils.ISO_DATE_FORMAT.format(time);
        System.out.println(str);
        assertEquals("2019-01-01",str);
    }
    @Test
    public void getYearEnd() {
        YearMoonTools yearMoonTools = new YearMoonTools();

        Calendar time = yearMoonTools.getYearEnd("2019");
        String str = DateFormatUtils.ISO_DATE_FORMAT.format(time);
        System.out.println(str);
        assertEquals("2019-12-31",str);
    }
}