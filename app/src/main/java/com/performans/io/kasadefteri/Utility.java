package com.performans.io.kasadefteri;


import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {


    private static final String DATE_STRING_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";


    public static String getNow() {
        return getNow(Calendar.getInstance().getTime(), DATE_STRING_FORMAT);
    }

    public static String getNow(String format) {
        return getNow(Calendar.getInstance().getTime(), format);
    }

    public static String getNow(Date date) {
        return getNow(date, DATE_STRING_FORMAT);
    }

    public static String getNow(long timeInMillis) {
        return getNow(new Date(timeInMillis), DATE_STRING_FORMAT);
    }

    public static String getNow(Date date, String format) {
        if (date == null) date = Calendar.getInstance().getTime();
        if (isNullOrWhitespace(format)) format = DATE_STRING_FORMAT;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static long getLongFromStringTime(String timeString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date date = sdf.parse(timeString);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrWhitespace(String s) {
        return s == null || isWhitespace(s);

    }

    public static boolean isWhitespace(String s) {
        try {
            int length = s.length();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    if (!Character.isWhitespace(s.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
