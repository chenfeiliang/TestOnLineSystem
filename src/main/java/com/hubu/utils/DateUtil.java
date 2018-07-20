package com.hubu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String  DateToString(Date date){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String dateString = formatter.format(date);

        return dateString;
    }

    public static long calLastedTime(Date startDate,Date endDate) {
        long a = startDate.getTime();
        long b = endDate.getTime();
        long c = (int)((b - a) / 1000);
        if(c<0){
            c = 0 ;
        }
        return c;
    }
}
