/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.ejb;

import com.thaisoftplus.aup.business.ApiSettingBusiness;
import com.thaisoftplus.aup.jpa.entity.ApiSetting;
import com.thaisoftplus.aup.query.ApiSettingQuery;
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
@Stateless(name = "ApiSettingSessionBean", mappedName = "apiSettingSessionBean")
public class ApiSettingSessionBean implements ApiSettingSessionBeanLocal, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ApiSettingSessionBean.class);

    @PersistenceContext(unitName = "aup")
    private EntityManager em;

    @Override
    public void create(ApiSetting setting) throws Exception {
        logger.trace("Begin create...");
        ApiSettingBusiness business = new ApiSettingBusiness(setting);
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
            ApiSettingBusiness business = new ApiSettingBusiness(ApiSettingQuery.getLastedSetting(em));
            business.updateContext();
        } finally {
            logger.trace("End init...");
        }
    }

    @Override
    public ApiSetting getLastedSetting() {
        logger.trace("Begin getLastedSetting...");
        try {
            return ApiSettingQuery.getLastedSetting(em);
        } finally {
            logger.trace("End getLastedSetting...");
        }
    }
}
