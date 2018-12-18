package com.example.administrator.warehousemanagementsystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: ZhongMing
 * DATE: 2018/12/17 0017
 * Description:
 **/
public class TimeUtil {
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

}
