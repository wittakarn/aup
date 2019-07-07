/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.util;

import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 *
 * @author witta
 */
public class DateHelper {

    public static void main(String[] args) {
        System.out.println(String.valueOf(getCurrentHour("Asia/Bangkok")));
        System.out.println(String.valueOf(getCurrentHour("America/Chicago")));
    }

    public static int getCurrentHour(String timezone) {
        LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone(timezone).toZoneId());
        return localNow.getHour();
    }
}
