/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author witta
 */
@Entity
@Table(name = "sheet_setting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SheetSetting.findAll", query = "SELECT s FROM SheetSetting s"),
    @NamedQuery(name = "SheetSetting.findByVersion", query = "SELECT s FROM SheetSetting s WHERE s.sheetSettingPK.version = :version"),
    @NamedQuery(name = "SheetSetting.findBySeq", query = "SELECT s FROM SheetSetting s WHERE s.sheetSettingPK.seq = :seq"),
    @NamedQuery(name = "SheetSetting.findBySheetId", query = "SELECT s FROM SheetSetting s WHERE s.sheetId = :sheetId")})
public class SheetSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SheetSettingPK sheetSettingPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sheet_id")
    private String sheetId;

    public SheetSetting() {
    }

    public SheetSetting(SheetSettingPK sheetSettingPK) {
        this.sheetSettingPK = sheetSettingPK;
    }

    public SheetSetting(SheetSettingPK sheetSettingPK, String sheetId) {
        this.sheetSettingPK = sheetSettingPK;
        this.sheetId = sheetId;
    }

    public SheetSetting(int version, int seq) {
        this.sheetSettingPK = new SheetSettingPK(version, seq);
    }

    public SheetSettingPK getSheetSettingPK() {
        return sheetSettingPK;
    }

    public void setSheetSettingPK(SheetSettingPK sheetSettingPK) {
        this.sheetSettingPK = sheetSettingPK;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sheetSettingPK != null ? sheetSettingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SheetSetting)) {
            return false;
        }
        SheetSetting other = (SheetSetting) object;
        if ((this.sheetSettingPK == null && other.sheetSettingPK != null) || (this.sheetSettingPK != null && !this.sheetSettingPK.equals(other.sheetSettingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.thaisoftplus.aup.jpa.entity.SheetSetting[ sheetSettingPK=" + sheetSettingPK + " ]";
    }
    
}
