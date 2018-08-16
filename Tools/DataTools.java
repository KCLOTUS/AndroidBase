package com.flytoyou.baseapplication.Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具
 * @author flytoyou
 * @version 1.0.0
 */
public class DataTools {

    /**
     * 获取相对今天来说的某天的日期
     * @param distanceDay 0今天 -1昨天 1明天
     */
    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy年M月d日");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 获取相对今天来说的某天的日期
     * @param distanceDay 0今天 -1昨天 1明天
     * @return
     */
    public static String getYeatandMonth(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-M");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 获取相对今天来说的某天的日期
     * @param distanceDay 0今天 -1昨天 1明天
     * @return
     */
    public static String getMonth(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("M");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 获取相对今天来说的某天的日期
     * @param distanceDay 0今天 -1昨天 1明天
     * @return
     */
    public static String getOldDateCode(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-M-d");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

}
