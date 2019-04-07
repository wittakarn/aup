/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.test;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author witta
 */
public class DateConvertTest {

    public static void main(String[] args) {
        Date currentDate = new Date();
        Calendar nextHour = Calendar.getInstance();
        nextHour.add(Calendar.MINUTE, 60);

        System.out.println(currentDate.getTime() + "/" + nextHour.getTime().getTime());
        System.out.println(compareTwoTimeStamps(currentDate.getTime(), nextHour.getTime().getTime()));
    }

    private static long compareTwoTimeStamps(long startTimeStamp, long endTimeStamp) {
        long diff = endTimeStamp - startTimeStamp;
        long diffMinutes = diff / (60 * 1000);

        return diffMinutes;
    }
}
