/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.ejb;

import com.thaisoftplus.aup.business.UserSettingBusiness;
import com.thaisoftplus.aup.jpa.entity.UserSetting;
import com.thaisoftplus.aup.query.UserSettingQuery;
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
@Stateless(name = "UserSettingSessionBean", mappedName = "userSettingSessionBean")
public class UserSettingSessionBean implements UserSettingSessionBeanLocal, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(UserSettingSessionBean.class);

    @PersistenceContext(unitName = "aup")
    private EntityManager em;

    @Override
    public void create(UserSetting userSetting) throws Exception {
        logger.trace("Begin create...");
        UserSettingBusiness business = new UserSettingBusiness(userSetting);
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
            UserSettingBusiness business = new UserSettingBusiness(UserSettingQuery.getLastedSetting(em));
            business.updateContext();
        } finally {
            logger.trace("End init...");
        }
    }

    @Override
    public UserSetting getLastedSetting() {
        logger.trace("Begin getLastedSetting...");
        try {
            return UserSettingQuery.getLastedSetting(em);
        } finally {
            logger.trace("End getLastedSetting...");
        }
    }
}
