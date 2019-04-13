/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.ejb;

import com.thaisoftplus.aup.business.SheetSettingBusiness;
import com.thaisoftplus.aup.jpa.entity.SheetSetting;
import com.thaisoftplus.aup.query.SheetSettingQuery;
import java.io.Serializable;
import java.util.List;
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
@Stateless(name = "SheetSettingSessionBean", mappedName = "sheetSettingSessionBean")
public class SheetSettingSessionBean implements SheetSettingSessionBeanLocal, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(SheetSettingSessionBean.class);

    @PersistenceContext(unitName = "aup")
    private EntityManager em;

    @Override
    public void create(List<SheetSetting> settings) throws Exception {
        logger.trace("Begin create...");
        SheetSettingBusiness business = new SheetSettingBusiness(settings);
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
            SheetSettingBusiness business = new SheetSettingBusiness(SheetSettingQuery.getLastedSetting(em));
            business.updateContext(em);
        } finally {
            logger.trace("End init...");
        }
    }

    @Override
    public List<SheetSetting> getLastedSetting() {
        logger.trace("Begin getLastedSetting...");
        try {
            return SheetSettingQuery.getLastedSetting(em);
        } finally {
            logger.trace("End getLastedSetting...");
        }
    }
}
