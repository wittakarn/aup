/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.business;

import com.thaisoftplus.aup.jpa.entity.SheetSetting;
import com.thaisoftplus.aup.query.SheetSettingQuery;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author witta
 */
public class SheetSettingBusiness {

    private List<SheetSetting> settings;

    public SheetSettingBusiness(List<SheetSetting> settings) {
        this.settings = settings;
    }

    public void createSetting(EntityManager em) {
        int nextVersion = SheetSettingQuery.getLastedVersion(em) + 1;
        for (SheetSetting setting : settings) {
            setting.getSheetSettingPK().setVersion(nextVersion);
            em.persist(setting);
        }
        updateContext();
    }

    public void updateContext() {
//        ApplicationContext.AMAZON_USER = setting.getAmazonUser();
//        ApplicationContext.AMAZON_PASSWORD = setting.getAmazonPassword();
//        ApplicationContext.BEFRUGAL_USER = setting.getBefrugalUser();
//        ApplicationContext.BEFRUGAL_PASSWORD = setting.getBefrugalPassword();
    }
}
