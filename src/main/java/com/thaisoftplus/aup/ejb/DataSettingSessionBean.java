/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.ejb;

import com.thaisoftplus.aup.business.DataSettingBusiness;
import com.thaisoftplus.aup.jpa.entity.DataSetting;
import com.thaisoftplus.aup.query.DataSettingQuery;
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
@Stateless(name = "DataSettingSessionBean", mappedName = "dataSettingSessionBean")
public class DataSettingSessionBean implements DataSettingSessionBeanLocal, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(DataSettingSessionBean.class);

    @PersistenceContext(unitName = "aup")
    private EntityManager em;

    @Override
    public void create(DataSetting setting) throws Exception {
        logger.trace("Begin create...");
        DataSettingBusiness business = new DataSettingBusiness(setting);
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
            DataSettingBusiness business = new DataSettingBusiness(DataSettingQuery.getLastedSetting(em));
            business.updateContext();
        } finally {
            logger.trace("End init...");
        }
    }

    @Override
    public DataSetting getLastedSetting() {
        logger.trace("Begin getLastedSetting...");
        try {
            return DataSettingQuery.getLastedSetting(em);
        } finally {
            logger.trace("End getLastedSetting...");
        }
    }
}
