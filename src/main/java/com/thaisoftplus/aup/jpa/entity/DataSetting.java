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
@Table(name = "data_setting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataSetting.findAll", query = "SELECT d FROM DataSetting d"),
    @NamedQuery(name = "DataSetting.findByVersion", query = "SELECT d FROM DataSetting d WHERE d.version = :version"),
    @NamedQuery(name = "DataSetting.findBySheetName", query = "SELECT d FROM DataSetting d WHERE d.sheetName = :sheetName"),
    @NamedQuery(name = "DataSetting.findByStartRow", query = "SELECT d FROM DataSetting d WHERE d.startRow = :startRow"),
    @NamedQuery(name = "DataSetting.findByLink", query = "SELECT d FROM DataSetting d WHERE d.link = :link"),
    @NamedQuery(name = "DataSetting.findByAsin", query = "SELECT d FROM DataSetting d WHERE d.asin = :asin"),
    @NamedQuery(name = "DataSetting.findByOldData", query = "SELECT d FROM DataSetting d WHERE d.oldData = :oldData"),
    @NamedQuery(name = "DataSetting.findByNewData", query = "SELECT d FROM DataSetting d WHERE d.newData = :newData"),
    @NamedQuery(name = "DataSetting.findByAsinCrawling", query = "SELECT d FROM DataSetting d WHERE d.asinCrawling = :asinCrawling"),
    @NamedQuery(name = "DataSetting.findByReviewScore", query = "SELECT d FROM DataSetting d WHERE d.reviewScore = :reviewScore"),
    @NamedQuery(name = "DataSetting.findByReview", query = "SELECT d FROM DataSetting d WHERE d.review = :review"),
    @NamedQuery(name = "DataSetting.findBySellerName1", query = "SELECT d FROM DataSetting d WHERE d.sellerName1 = :sellerName1"),
    @NamedQuery(name = "DataSetting.findByPrice1", query = "SELECT d FROM DataSetting d WHERE d.price1 = :price1"),
    @NamedQuery(name = "DataSetting.findByShipping1", query = "SELECT d FROM DataSetting d WHERE d.shipping1 = :shipping1"),
    @NamedQuery(name = "DataSetting.findByAddOn1", query = "SELECT d FROM DataSetting d WHERE d.addOn1 = :addOn1"),
    @NamedQuery(name = "DataSetting.findByType1", query = "SELECT d FROM DataSetting d WHERE d.type1 = :type1"),
    @NamedQuery(name = "DataSetting.findByWid1", query = "SELECT d FROM DataSetting d WHERE d.wid1 = :wid1"),
    @NamedQuery(name = "DataSetting.findBySellerName2", query = "SELECT d FROM DataSetting d WHERE d.sellerName2 = :sellerName2"),
    @NamedQuery(name = "DataSetting.findByPrice2", query = "SELECT d FROM DataSetting d WHERE d.price2 = :price2"),
    @NamedQuery(name = "DataSetting.findByShipping2", query = "SELECT d FROM DataSetting d WHERE d.shipping2 = :shipping2"),
    @NamedQuery(name = "DataSetting.findByAddOn2", query = "SELECT d FROM DataSetting d WHERE d.addOn2 = :addOn2"),
    @NamedQuery(name = "DataSetting.findByType2", query = "SELECT d FROM DataSetting d WHERE d.type2 = :type2"),
    @NamedQuery(name = "DataSetting.findByWid2", query = "SELECT d FROM DataSetting d WHERE d.wid2 = :wid2"),
    @NamedQuery(name = "DataSetting.findBySellerName3", query = "SELECT d FROM DataSetting d WHERE d.sellerName3 = :sellerName3"),
    @NamedQuery(name = "DataSetting.findByPrice3", query = "SELECT d FROM DataSetting d WHERE d.price3 = :price3"),
    @NamedQuery(name = "DataSetting.findByShipping3", query = "SELECT d FROM DataSetting d WHERE d.shipping3 = :shipping3"),
    @NamedQuery(name = "DataSetting.findByAddOn3", query = "SELECT d FROM DataSetting d WHERE d.addOn3 = :addOn3"),
    @NamedQuery(name = "DataSetting.findByType3", query = "SELECT d FROM DataSetting d WHERE d.type3 = :type3"),
    @NamedQuery(name = "DataSetting.findByWid3", query = "SELECT d FROM DataSetting d WHERE d.wid3 = :wid3")})
public class DataSetting implements Serializable {

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
    @Column(name = "link")
    private String link;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "asin")
    private String asin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "old_data")
    private String oldData;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "new_data")
    private String newData;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "asin_crawling")
    private String asinCrawling;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "review_score")
    private String reviewScore;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "review")
    private String review;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "seller_name_1")
    private String sellerName1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "price_1")
    private String price1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "shipping_1")
    private String shipping1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "add_on_1")
    private String addOn1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "type_1")
    private String type1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "wid_1")
    private String wid1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "seller_name_2")
    private String sellerName2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "price_2")
    private String price2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "shipping_2")
    private String shipping2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "add_on_2")
    private String addOn2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "type_2")
    private String type2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "wid_2")
    private String wid2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "seller_name_3")
    private String sellerName3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "price_3")
    private String price3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "shipping_3")
    private String shipping3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "add_on_3")
    private String addOn3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "type_3")
    private String type3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "wid_3")
    private String wid3;

    public DataSetting() {
    }

    public DataSetting(Integer version) {
        this.version = version;
    }

    public DataSetting(Integer version, String sheetName, int startRow, String link, String asin, String oldData, String newData, String asinCrawling, String reviewScore, String review, String sellerName1, String price1, String shipping1, String addOn1, String type1, String wid1, String sellerName2, String price2, String shipping2, String addOn2, String type2, String wid2, String sellerName3, String price3, String shipping3, String addOn3, String type3, String wid3) {
        this.version = version;
        this.sheetName = sheetName;
        this.startRow = startRow;
        this.link = link;
        this.asin = asin;
        this.oldData = oldData;
        this.newData = newData;
        this.asinCrawling = asinCrawling;
        this.reviewScore = reviewScore;
        this.review = review;
        this.sellerName1 = sellerName1;
        this.price1 = price1;
        this.shipping1 = shipping1;
        this.addOn1 = addOn1;
        this.type1 = type1;
        this.wid1 = wid1;
        this.sellerName2 = sellerName2;
        this.price2 = price2;
        this.shipping2 = shipping2;
        this.addOn2 = addOn2;
        this.type2 = type2;
        this.wid2 = wid2;
        this.sellerName3 = sellerName3;
        this.price3 = price3;
        this.shipping3 = shipping3;
        this.addOn3 = addOn3;
        this.type3 = type3;
        this.wid3 = wid3;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getOldData() {
        return oldData;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }

    public String getAsinCrawling() {
        return asinCrawling;
    }

    public void setAsinCrawling(String asinCrawling) {
        this.asinCrawling = asinCrawling;
    }

    public String getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(String reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getSellerName1() {
        return sellerName1;
    }

    public void setSellerName1(String sellerName1) {
        this.sellerName1 = sellerName1;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getShipping1() {
        return shipping1;
    }

    public void setShipping1(String shipping1) {
        this.shipping1 = shipping1;
    }

    public String getAddOn1() {
        return addOn1;
    }

    public void setAddOn1(String addOn1) {
        this.addOn1 = addOn1;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getWid1() {
        return wid1;
    }

    public void setWid1(String wid1) {
        this.wid1 = wid1;
    }

    public String getSellerName2() {
        return sellerName2;
    }

    public void setSellerName2(String sellerName2) {
        this.sellerName2 = sellerName2;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getShipping2() {
        return shipping2;
    }

    public void setShipping2(String shipping2) {
        this.shipping2 = shipping2;
    }

    public String getAddOn2() {
        return addOn2;
    }

    public void setAddOn2(String addOn2) {
        this.addOn2 = addOn2;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getWid2() {
        return wid2;
    }

    public void setWid2(String wid2) {
        this.wid2 = wid2;
    }

    public String getSellerName3() {
        return sellerName3;
    }

    public void setSellerName3(String sellerName3) {
        this.sellerName3 = sellerName3;
    }

    public String getPrice3() {
        return price3;
    }

    public void setPrice3(String price3) {
        this.price3 = price3;
    }

    public String getShipping3() {
        return shipping3;
    }

    public void setShipping3(String shipping3) {
        this.shipping3 = shipping3;
    }

    public String getAddOn3() {
        return addOn3;
    }

    public void setAddOn3(String addOn3) {
        this.addOn3 = addOn3;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getWid3() {
        return wid3;
    }

    public void setWid3(String wid3) {
        this.wid3 = wid3;
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
        if (!(object instanceof DataSetting)) {
            return false;
        }
        DataSetting other = (DataSetting) object;
        if ((this.version == null && other.version != null) || (this.version != null && !this.version.equals(other.version))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.thaisoftplus.aup.jpa.entity.DataSetting[ version=" + version + " ]";
    }
    
}
