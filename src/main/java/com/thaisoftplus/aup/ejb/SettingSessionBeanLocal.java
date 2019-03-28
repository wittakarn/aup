/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.ejb;

import com.thaisoftplus.aup.jpa.entity.Setting;
import java.io.Serializable;
import javax.ejb.Remote;

/**
 *
 * @author witta
 */
@Remote
public interface SettingSessionBeanLocal extends Serializable {

    public void create(Setting setting) throws Exception;
    public void init();
    public Setting getLastedSetting();
}
