/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.context;

import java.util.HashMap;

/**
 *
 * @author witta
 */
public class ApplicationContext {

    public static HashMap<String, String> configData = new HashMap<String, String>();
    public static boolean isRunning = false;

    public static final String CHROME_DRIVER_PATH = "aup";
    public static final String APPLICATION_NAME = "aup";
    public static final String TOKENS_DIRECTORY_PATH = "D:\\aup\\tokens";
    public static final String CREDENTIALS_FILE_PATH = "D:\\aup\\credentials.json";
    public static final String USER_DATA_PATH_CONFIG = "USER_DATA_PATH";

    public static String AMAZON_USER = "Rufussa6594@gmail.com";
    public static String AMAZON_PASSWORD = "VS8X0UYUDz8A";
    public static String BEFRUGAL_USER = "CashbacktestAA19@gmail.com";
    public static String BEFRUGAL_PASSWORD = "Cash15Cash";

    public static String SPREAD_SHEET_ID = "1aACCABPWR2JEpWZs5QkoUFlzJHdcvLpmOlGvZKR4gQk";
    public static String SPREAD_TAB_NAME = "Input File";
    public static int START_ROW_INDEX = 7;
    public static String STAFF_NAME_COLUMN = "D";
    public static String VENDOR_COLUMN = "E";
    public static String SHIPPING_NAME_COLUMN = "G";
    public static String SHIPPING_LAST_NAME_COLUMN = "H";
    public static String ADDRESS_1_COLUMN = "I";
    public static String ADDRESS_2_COLUMN = "J";
    public static String CITY_COLUMN = "K";
    public static String STATE_COLUMN = "L";
    public static String ZIP_COLUMN = "M";
    public static String PHONE_COLUMN = "N";
    public static String TO_BUY_COLUMN = "V";
    public static String PAY_TO_VENDOR_COLUMN = "W";
    public static String ORDER_PROCESS_COLUMN = "X";
    public static String VENDOR_ID_COLUMN = "Z";

    public static String getUserDataPath() {
        return ApplicationContext.configData.get(USER_DATA_PATH_CONFIG) != null
                ? ApplicationContext.configData.get(USER_DATA_PATH_CONFIG)
                : "C:\\Users\\witta\\AppData\\Local\\Google\\Chrome\\User Data";
    }
}
