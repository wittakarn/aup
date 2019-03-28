/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.query;

import com.thaisoftplus.aup.jpa.entity.Setting;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wittakarn
 */
public class SettingQuery implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(SettingQuery.class);

    public static Setting getLastedSetting(EntityManager em) {
        logger.trace("Begin getLastedId");
        try {
            Query query = em.createQuery("SELECT max(s.id) FROM Setting s");
            Object lastedIndexObj = query.getSingleResult();
            int lastedIndex = 0;
            if (lastedIndexObj != null) {
                lastedIndex = (int) lastedIndexObj;
                query = em.createQuery("SELECT s FROM Setting s WHERE s.id = :id");
                query.setParameter("id", lastedIndex);
                return (Setting) query.getSingleResult();
            } else {
                return null;
            }
        } finally {
            logger.trace("End getLastedId");
        }
    }
}
