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
    public static final String TOKENS_DIRECTORY_PATH = "D:\\aup\\tokens";
    public static final String CREDENTIALS_FILE_PATH = "D:\\aup\\credentials.json";
    public static final String USER_DATA_PATH_CONFIG = "USER_DATA_PATH";

    public static int SHEET_INDEX = 0;
    public static int START_ROW_INDEX = 3;

    public static String DATA_SHEET_NAME;
    public static String LINK = "A";
    public static String ASIN = "C";
    public static String OLD_DATA = "D";
    public static String NEW_DATA = "E";
    public static String ASIN_CRAWLING = "G";
    public static String REVIEW_SCORE = "H";
    public static String REVIEW = "I";
    public static String SELLER_NAME_1 = "J";
    public static String PRICE_1 = "K";
    public static String SHIPING_1 = "L";
    public static String ADD_ON_1 = "M";
    public static String TYPE_1 = "N";
    public static String WID_1 = "O";
    public static String SELLER_NAME_2 = "P";
    public static String PRICE_2 = "Q";
    public static String SHIPING_2 = "R";
    public static String ADD_ON_2 = "S";
    public static String TYPE_2 = "T";
    public static String WID_2 = "U";
    public static String SELLER_NAME_3 = "V";
    public static String PRICE_3 = "W";
    public static String SHIPING_3 = "X";
    public static String ADD_ON_3 = "Y";
    public static String TYPE_3 = "Z";
    public static String WID_3 = "AA";
    
    public static String API_SHEET_NAME;
    public static int API_START_ROW;

    public static String getUserDataPath() {
        return ApplicationContext.configData.get(USER_DATA_PATH_CONFIG) != null
                ? ApplicationContext.configData.get(USER_DATA_PATH_CONFIG)
                : "C:\\Users\\witta\\AppData\\Local\\Google\\Chrome\\User Data";
    }
    
    public static String getSheetId() {
        return sheetSetting.get(SHEET_INDEX).getSheetId();
    }
}
