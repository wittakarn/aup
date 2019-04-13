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
@Table(name = "api_setting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApiSetting.findAll", query = "SELECT a FROM ApiSetting a"),
    @NamedQuery(name = "ApiSetting.findByVersion", query = "SELECT a FROM ApiSetting a WHERE a.version = :version"),
    @NamedQuery(name = "ApiSetting.findBySheetName", query = "SELECT a FROM ApiSetting a WHERE a.sheetName = :sheetName"),
    @NamedQuery(name = "ApiSetting.findByStartRow", query = "SELECT a FROM ApiSetting a WHERE a.startRow = :startRow"),
    @NamedQuery(name = "ApiSetting.findByUpdateStatus", query = "SELECT a FROM ApiSetting a WHERE a.updateStatus = :updateStatus"),
    @NamedQuery(name = "ApiSetting.findBySku", query = "SELECT a FROM ApiSetting a WHERE a.sku = :sku"),
    @NamedQuery(name = "ApiSetting.findByAsinApi", query = "SELECT a FROM ApiSetting a WHERE a.asinApi = :asinApi"),
    @NamedQuery(name = "ApiSetting.findByPriceApi", query = "SELECT a FROM ApiSetting a WHERE a.priceApi = :priceApi"),
    @NamedQuery(name = "ApiSetting.findByMinAllowPrice", query = "SELECT a FROM ApiSetting a WHERE a.minAllowPrice = :minAllowPrice"),
    @NamedQuery(name = "ApiSetting.findByMaxAllowPrice", query = "SELECT a FROM ApiSetting a WHERE a.maxAllowPrice = :maxAllowPrice"),
    @NamedQuery(name = "ApiSetting.findByQuantity", query = "SELECT a FROM ApiSetting a WHERE a.quantity = :quantity"),
    @NamedQuery(name = "ApiSetting.findByLeadTime", query = "SELECT a FROM ApiSetting a WHERE a.leadTime = :leadTime"),
    @NamedQuery(name = "ApiSetting.findByTimestamp", query = "SELECT a FROM ApiSetting a WHERE a.timestamp = :timestamp")})
public class ApiSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "version")
    private Integer version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sheet_name")
    private String sheetName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_row")
    private int startRow;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "update_status")
    private String updateStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "sku")
    private String sku;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "asin_api")
    private String asinApi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "price_api")
    private String priceApi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "min_allow_price")
    private String minAllowPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "max_allow_price")
    private String maxAllowPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "quantity")
    private String quantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "lead_time")
    private String leadTime;
    @Size(max = 3)
    @Column(name = "timestamp")
    private String timestamp;

    public ApiSetting() {
    }

    public ApiSetting(Integer version) {
        this.version = version;
    }

    public ApiSetting(Integer version, String sheetName, int startRow, String updateStatus, String sku, String asinApi, String priceApi, String minAllowPrice, String maxAllowPrice, String quantity, String leadTime) {
        this.version = version;
        this.sheetName = sheetName;
        this.startRow = startRow;
        this.updateStatus = updateStatus;
        this.sku = sku;
        this.asinApi = asinApi;
        this.priceApi = priceApi;
        this.minAllowPrice = minAllowPrice;
        this.maxAllowPrice = maxAllowPrice;
        this.quantity = quantity;
        this.leadTime = leadTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getAsinApi() {
        return asinApi;
    }

    public void setAsinApi(String asinApi) {
        this.asinApi = asinApi;
    }

    public String getPriceApi() {
        return priceApi;
    }

    public void setPriceApi(String priceApi) {
        this.priceApi = priceApi;
    }

    public String getMinAllowPrice() {
        return minAllowPrice;
    }

    public void setMinAllowPrice(String minAllowPrice) {
        this.minAllowPrice = minAllowPrice;
    }

    public String getMaxAllowPrice() {
        return maxAllowPrice;
    }

    public void setMaxAllowPrice(String maxAllowPrice) {
        this.maxAllowPrice = maxAllowPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (version != null ? version.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApiSetting)) {
            return false;
        }
        ApiSetting other = (ApiSetting) object;
        if ((this.version == null && other.version != null) || (this.version != null && !this.version.equals(other.version))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.thaisoftplus.aup.jpa.entity.ApiSetting[ version=" + version + " ]";
    }
    
}
