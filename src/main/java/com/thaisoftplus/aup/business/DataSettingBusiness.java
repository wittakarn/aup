/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.business;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.jpa.entity.DataSetting;
import javax.persistence.EntityManager;

/**
 *
 * @author witta
 */
public class DataSettingBusiness {

    private DataSetting setting;

    public DataSettingBusiness(DataSetting setting) {
        this.setting = setting;
    }

    public void createSetting(EntityManager em) {
        em.persist(this.setting);
        updateContext();
    }

    public void updateContext() {
        ApplicationContext.DATA_SHEET_NAME = setting.getSheetName();
        ApplicationContext.START_ROW_INDEX = setting.getStartRow();
        ApplicationContext.LINK = setting.getLink();
        ApplicationContext.ASIN = setting.getAsin();
        ApplicationContext.OLD_DATA = setting.getOldData();
        ApplicationContext.NEW_DATA = setting.getNewData();
        ApplicationContext.ASIN_CRAWLING = setting.getAsinCrawling();
        ApplicationContext.REVIEW_SCORE = setting.getReviewScore();
        ApplicationContext.REVIEW = setting.getReview();
        ApplicationContext.SELLER_NAME_1 = setting.getSellerName1();
        ApplicationContext.PRICE_1 = setting.getPrice1();
        ApplicationContext.SHIPING_1 = setting.getShipping1();
        ApplicationContext.ADD_ON_1 = setting.getAddOn1();
        ApplicationContext.TYPE_1 = setting.getType1();
        ApplicationContext.WID_1 = setting.getWid1();
        ApplicationContext.SELLER_NAME_2 = setting.getSellerName2();
        ApplicationContext.PRICE_2 = setting.getPrice2();
        ApplicationContext.SHIPING_2 = setting.getShipping2();
        ApplicationContext.ADD_ON_2 = setting.getAddOn2();
        ApplicationContext.TYPE_2 = setting.getType2();
        ApplicationContext.WID_2 = setting.getWid2();
        ApplicationContext.SELLER_NAME_3 = setting.getSellerName3();
        ApplicationContext.PRICE_3 = setting.getPrice3();
        ApplicationContext.SHIPING_3 = setting.getShipping3();
        ApplicationContext.ADD_ON_3 = setting.getAddOn3();
        ApplicationContext.TYPE_3 = setting.getType3();
        ApplicationContext.WID_3 = setting.getWid3();
    }
}
