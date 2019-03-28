/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.business;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.jpa.entity.UserSetting;
import javax.persistence.EntityManager;

/**
 *
 * @author witta
 */
public class UserSettingBusiness {

    private UserSetting setting;

    public UserSettingBusiness(UserSetting setting) {
        this.setting = setting;
    }

    public void createSetting(EntityManager em) {
        em.persist(this.setting);
        updateContext();
    }

    public void updateContext() {
        ApplicationContext.AMAZON_USER = setting.getAmazonUser();
        ApplicationContext.AMAZON_PASSWORD = setting.getAmazonPassword();
        ApplicationContext.BEFRUGAL_USER = setting.getBefrugalUser();
        ApplicationContext.BEFRUGAL_PASSWORD = setting.getBefrugalPassword();
    }
}
