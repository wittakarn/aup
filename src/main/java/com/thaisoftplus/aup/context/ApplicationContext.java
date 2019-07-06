/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.context;

import com.thaisoftplus.aup.jpa.entity.SheetSetting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author witta
 */
public class ApplicationContext {

    public static HashMap<String, String> configData = new HashMap<String, String>();
    public static List<SheetSetting> sheetSetting = new ArrayList<SheetSetting>();
    public static boolean isRunning = false;

    public static final String CHROME_DRIVER_PATH = "aup";
    public static final String APPLICATION_NAME = "aup";
    public static final String BROWSE_DRIVER_PATH = "BROWSE_DRIVER_PATH";
    public static final String BROWSE_BINARY_PATH = "BROWSE_BINARY_PATH";
    public static final String BROWSE_PROFILE_PATH = "BROWSE_PROFILE_PATH";
    public static final String TOKENS_DIRECTORY_PATH = "TOKENS_DIRECTORY_PATH";
    public static final String CREDENTIALS_FILE_PATH = "CREDENTIALS_FILE_PATH";
    public static final String USER_DATA_PATH_CONFIG = "USER_DATA_PATH";
    public static final String THREAD_SIZE = "THREAD_SIZE";

    public static int SHEET_INDEX = 0;
    public static int START_ROW_INDEX = 3;

    public static String DATA_SHEET_NAME;
    public static String LINK = "A";
    public static String OLD_DATA = "D";
    public static String NEW_DATA = "E";
    public static String ASIN_CRAWLING = "G";
    public static String SELLER_NAME_1 = "H";
    public static String PRICE_1 = "I";
    public static String SHIPING_1 = "J";
    public static String ADD_ON_1 = "K";
    public static String TYPE_1 = "L";
    public static String WID_1 = "M";
    public static String SELLER_NAME_2 = "N";
    public static String PRICE_2 = "O";
    public static String SHIPING_2 = "P";
    public static String ADD_ON_2 = "Q";
    public static String TYPE_2 = "R";
    public static String WID_2 = "S";
    public static String SELLER_NAME_3 = "T";
    public static String PRICE_3 = "U";
    public static String SHIPING_3 = "V";
    public static String ADD_ON_3 = "W";
    public static String TYPE_3 = "X";
    public static String WID_3 = "Y";

    public static String API_SHEET_NAME;
    public static int API_START_ROW;

    public static String getUserDataPath() {
        return ApplicationContext.configData.get(USER_DATA_PATH_CONFIG) != null
                ? ApplicationContext.configData.get(USER_DATA_PATH_CONFIG)
                : "D:\\firefox-profiles\\profile";
    }
    
    public static String getBrowserDriverPath() {
        return ApplicationContext.configData.get(BROWSE_DRIVER_PATH) != null
                ? ApplicationContext.configData.get(BROWSE_DRIVER_PATH)
                : "D:\\geckodriver.exe";
    }
    
    public static String getBrowserBinaryPath() {
        return ApplicationContext.configData.get(BROWSE_BINARY_PATH) != null
                ? ApplicationContext.configData.get(BROWSE_BINARY_PATH)
                : "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    }
    
    public static String getBrowserProfilePath() {
        return ApplicationContext.configData.get(BROWSE_PROFILE_PATH) != null
                ? ApplicationContext.configData.get(BROWSE_PROFILE_PATH)
                : "D:\\firefox-profiles/profile";
    }

    public static String getSheetId() {
        return sheetSetting.get(SHEET_INDEX).getSheetId();
    }
}
