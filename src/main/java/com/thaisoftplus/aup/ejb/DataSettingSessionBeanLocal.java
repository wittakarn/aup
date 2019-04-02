/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.ejb;

import com.thaisoftplus.aup.jpa.entity.DataSetting;
import java.io.Serializable;
import javax.ejb.Remote;

/**
 *
 * @author witta
 */
@Remote
public interface DataSettingSessionBeanLocal extends Serializable {

    public void create(DataSetting setting) throws Exception;
    public void init();
    public DataSetting getLastedSetting();
}
