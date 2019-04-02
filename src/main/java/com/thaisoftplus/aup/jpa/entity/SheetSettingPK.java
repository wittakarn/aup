/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author witta
 */
@Embeddable
public class SheetSettingPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private int version;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;

    public SheetSettingPK() {
    }

    public SheetSettingPK(int version, int seq) {
        this.version = version;
        this.seq = seq;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) version;
        hash += (int) seq;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SheetSettingPK)) {
            return false;
        }
        SheetSettingPK other = (SheetSettingPK) object;
        if (this.version != other.version) {
            return false;
        }
        if (this.seq != other.seq) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.thaisoftplus.aup.jpa.entity.SheetSettingPK[ version=" + version + ", seq=" + seq + " ]";
    }
    
}
