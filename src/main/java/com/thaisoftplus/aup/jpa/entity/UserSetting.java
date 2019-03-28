/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "user_setting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSetting.findAll", query = "SELECT u FROM UserSetting u"),
    @NamedQuery(name = "UserSetting.findById", query = "SELECT u FROM UserSetting u WHERE u.id = :id"),
    @NamedQuery(name = "UserSetting.findByAmazonUser", query = "SELECT u FROM UserSetting u WHERE u.amazonUser = :amazonUser"),
    @NamedQuery(name = "UserSetting.findByAmazonPassword", query = "SELECT u FROM UserSetting u WHERE u.amazonPassword = :amazonPassword"),
    @NamedQuery(name = "UserSetting.findByBefrugalUser", query = "SELECT u FROM UserSetting u WHERE u.befrugalUser = :befrugalUser"),
    @NamedQuery(name = "UserSetting.findByBefrugalPassword", query = "SELECT u FROM UserSetting u WHERE u.befrugalPassword = :befrugalPassword")})
public class UserSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "amazon_user")
    private String amazonUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "amazon_password")
    private String amazonPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "befrugal_user")
    private String befrugalUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "befrugal_password")
    private String befrugalPassword;

    public UserSetting() {
    }

    public UserSetting(Integer id) {
        this.id = id;
    }

    public UserSetting(Integer id, String amazonUser, String amazonPassword, String befrugalUser, String befrugalPassword) {
        this.id = id;
        this.amazonUser = amazonUser;
        this.amazonPassword = amazonPassword;
        this.befrugalUser = befrugalUser;
        this.befrugalPassword = befrugalPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmazonUser() {
        return amazonUser;
    }

    public void setAmazonUser(String amazonUser) {
        this.amazonUser = amazonUser;
    }

    public String getAmazonPassword() {
        return amazonPassword;
    }

    public void setAmazonPassword(String amazonPassword) {
        this.amazonPassword = amazonPassword;
    }

    public String getBefrugalUser() {
        return befrugalUser;
    }

    public void setBefrugalUser(String befrugalUser) {
        this.befrugalUser = befrugalUser;
    }

    public String getBefrugalPassword() {
        return befrugalPassword;
    }

    public void setBefrugalPassword(String befrugalPassword) {
        this.befrugalPassword = befrugalPassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSetting)) {
            return false;
        }
        UserSetting other = (UserSetting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.thaisoftplus.aup.jpa.entity.UserSetting[ id=" + id + " ]";
    }
    
}
