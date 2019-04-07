/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.ejb;

import com.thaisoftplus.aup.jpa.entity.SheetSetting;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author witta
 */
@Remote
public interface SheetSettingSessionBeanLocal extends Serializable {

    public void create(List<SheetSetting> settings) throws Exception;

    public void init();

    public List<SheetSetting> getLastedSetting();
}
