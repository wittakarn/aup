/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.business;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.jpa.entity.Setting;
import javax.persistence.EntityManager;

/**
 *
 * @author witta
 */
public class SettingBusiness {

    private Setting setting;

    public SettingBusiness(Setting setting) {
        this.setting = setting;
    }

    public void createSetting(EntityManager em) {
        em.persist(this.setting);
        updateContext();
    }

    public void updateContext() {
        ApplicationContext.SPREAD_SHEET_ID = setting.getSheetId();
        ApplicationContext.SPREAD_TAB_NAME = setting.getSheetTab();
        ApplicationContext.START_ROW_INDEX = setting.getStartRow();
        ApplicationContext.STAFF_NAME_COLUMN = setting.getStaffName();
        ApplicationContext.VENDOR_COLUMN = setting.getVendor();
        ApplicationContext.SHIPPING_NAME_COLUMN = setting.getShippingName();
        ApplicationContext.SHIPPING_LAST_NAME_COLUMN = setting.getShippingLastname();
        ApplicationContext.ADDRESS_1_COLUMN = setting.getAddress1();
        ApplicationContext.ADDRESS_2_COLUMN = setting.getAddress2();
        ApplicationContext.CITY_COLUMN = setting.getCity();
        ApplicationContext.STATE_COLUMN = setting.getState();
        ApplicationContext.ZIP_COLUMN = setting.getZip();
        ApplicationContext.PHONE_COLUMN = setting.getPhone();
        ApplicationContext.TO_BUY_COLUMN = setting.getToBuy();
        ApplicationContext.PAY_TO_VENDOR_COLUMN = setting.getPayToVendor();
        ApplicationContext.ORDER_PROCESS_COLUMN = setting.getOrderProcess();
        ApplicationContext.VENDOR_ID_COLUMN = setting.getVendorId();
    }
}
