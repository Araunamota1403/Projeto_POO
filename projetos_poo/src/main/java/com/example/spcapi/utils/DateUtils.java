package com.example.spcapi.utils;

public class DateUtils {
    private static final long MS_PER_DAY = 1000 * 60 * 60 * 24;

    public static long millisecondsToDays(long msDiff) {
        return msDiff / MS_PER_DAY;
    }

    public static String formatDaysMessage(long days) {
        return days + (days != 1 ? " dias" : " dia");
    }
}