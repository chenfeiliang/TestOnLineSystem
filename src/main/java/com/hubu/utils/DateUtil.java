package com.hubu.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String DateToString(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String dateString = formatter.format(date);

        return dateString;
    }

    public static long calLastedTime(Date startDate, Date endDate) {
        long a = startDate.getTime();
        long b = endDate.getTime();
        long c = (int) ((b - a) / 1000);
        if (c < 0) {
            c = 0;
        }
        return c;
    }

    public static Date StringToDate(String dt) {

        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date date = null;

        try {

            date = format1.parse(dt);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }
}
