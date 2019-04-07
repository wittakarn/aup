/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.query;

import com.thaisoftplus.aup.jpa.entity.SheetSetting;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wittakarn
 */
public class SheetSettingQuery implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(SheetSettingQuery.class);

    public static int getLastedVersion(EntityManager em) {
        logger.trace("Begin getLastedId");
        try {
            Query query = em.createQuery("SELECT max(s.sheetSettingPK.version) FROM SheetSetting s");
            Object lastedSetting = query.getSingleResult();
            int lastedIndex = 0;
            if (lastedSetting != null) {
                lastedIndex = (int) lastedSetting;
            }
            return lastedIndex;
        } finally {
            logger.trace("End getLastedId");
        }
    }

    public static List<SheetSetting> getLastedSetting(EntityManager em) {
        logger.trace("Begin getLastedId");
        try {
            int lastedVersion = getLastedVersion(em);
            if (lastedVersion == 0) {
                return null;
            } else {
                Query query = em.createQuery("SELECT s FROM SheetSetting s WHERE s.sheetSettingPK.version = :version");
                query.setParameter("version", lastedVersion);
                return query.getResultList();
            }
        } finally {
            logger.trace("End getLastedId");
        }
    }
}
