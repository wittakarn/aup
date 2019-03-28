/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.ejb;

import com.thaisoftplus.aup.business.SettingBusiness;
import com.thaisoftplus.aup.jpa.entity.Setting;
import com.thaisoftplus.aup.query.SettingQuery;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author witta
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(name = "SettingSessionBean", mappedName = "settingSessionBean")
public class SettingSessionBean implements SettingSessionBeanLocal, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(SettingSessionBean.class);

    @PersistenceContext(unitName = "aup")
    private EntityManager em;

    @Override
    public void create(Setting setting) throws Exception {
        logger.trace("Begin create...");
        SettingBusiness business = new SettingBusiness(setting);
        try {
            business.createSetting(em);
        } finally {
            logger.trace("End create...");
        }
    }
    
    @Override
    public void init() {
        logger.trace("Begin init...");
        try {
            SettingBusiness business = new SettingBusiness(SettingQuery.getLastedSetting(em));
            business.updateContext();
        } finally {
            logger.trace("End init...");
        }
    }

    @Override
    public Setting getLastedSetting() {
        logger.trace("Begin getLastedSetting...");
        try {
            return SettingQuery.getLastedSetting(em);
        } finally {
            logger.trace("End getLastedSetting...");
        }
    }
}
