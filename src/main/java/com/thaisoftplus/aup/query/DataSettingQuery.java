/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.query;

import com.thaisoftplus.aup.jpa.entity.DataSetting;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wittakarn
 */
public class DataSettingQuery implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(DataSettingQuery.class);

    public static DataSetting getLastedSetting(EntityManager em) {
        logger.trace("Begin getLastedId");
        try {
            Query query = em.createQuery("SELECT max(d.version) FROM DataSetting d");
            Object lastedIndexObj = query.getSingleResult();
            int lastedIndex = 0;
            if (lastedIndexObj != null) {
                lastedIndex = (int) lastedIndexObj;
                query = em.createQuery("SELECT d FROM DataSetting d WHERE d.version = :version");
                query.setParameter("version", lastedIndex);
                return (DataSetting) query.getSingleResult();
            } else {
                return null;
            }
        } finally {
            logger.trace("End getLastedId");
        }
    }
}
