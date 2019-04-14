/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.util;

/**
 *
 * @author witta
 */
public class StringHelper {

    public static String replace(String value, char oldChar, char newChar) {
        if (value != null) {
            return value.replace(oldChar, newChar);
        }
        return null;
    }
}
