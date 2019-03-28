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
@Table(name = "setting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Setting.findAll", query = "SELECT s FROM Setting s"),
    @NamedQuery(name = "Setting.findById", query = "SELECT s FROM Setting s WHERE s.id = :id"),
    @NamedQuery(name = "Setting.findBySheetId", query = "SELECT s FROM Setting s WHERE s.sheetId = :sheetId"),
    @NamedQuery(name = "Setting.findBySheetTab", query = "SELECT s FROM Setting s WHERE s.sheetTab = :sheetTab"),
    @NamedQuery(name = "Setting.findByStartRow", query = "SELECT s FROM Setting s WHERE s.startRow = :startRow"),
    @NamedQuery(name = "Setting.findByStaffName", query = "SELECT s FROM Setting s WHERE s.staffName = :staffName"),
    @NamedQuery(name = "Setting.findByVendor", query = "SELECT s FROM Setting s WHERE s.vendor = :vendor"),
    @NamedQuery(name = "Setting.findByShippingName", query = "SELECT s FROM Setting s WHERE s.shippingName = :shippingName"),
    @NamedQuery(name = "Setting.findByShippingLastname", query = "SELECT s FROM Setting s WHERE s.shippingLastname = :shippingLastname"),
    @NamedQuery(name = "Setting.findByAddress1", query = "SELECT s FROM Setting s WHERE s.address1 = :address1"),
    @NamedQuery(name = "Setting.findByAddress2", query = "SELECT s FROM Setting s WHERE s.address2 = :address2"),
    @NamedQuery(name = "Setting.findByCity", query = "SELECT s FROM Setting s WHERE s.city = :city"),
    @NamedQuery(name = "Setting.findByState", query = "SELECT s FROM Setting s WHERE s.state = :state"),
    @NamedQuery(name = "Setting.findByZip", query = "SELECT s FROM Setting s WHERE s.zip = :zip"),
    @NamedQuery(name = "Setting.findByPhone", query = "SELECT s FROM Setting s WHERE s.phone = :phone"),
    @NamedQuery(name = "Setting.findByToBuy", query = "SELECT s FROM Setting s WHERE s.toBuy = :toBuy"),
    @NamedQuery(name = "Setting.findByPayToVendor", query = "SELECT s FROM Setting s WHERE s.payToVendor = :payToVendor"),
    @NamedQuery(name = "Setting.findByOrderProcess", query = "SELECT s FROM Setting s WHERE s.orderProcess = :orderProcess"),
    @NamedQuery(name = "Setting.findByVendorId", query = "SELECT s FROM Setting s WHERE s.vendorId = :vendorId")})
public class Setting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sheet_id")
    private String sheetId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "sheet_tab")
    private String sheetTab;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_row")
    private int startRow;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "staff_name")
    private String staffName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "vendor")
    private String vendor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "shipping_name")
    private String shippingName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "shipping_lastname")
    private String shippingLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "address1")
    private String address1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "address2")
    private String address2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "zip")
    private String zip;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "to_buy")
    private String toBuy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "pay_to_vendor")
    private String payToVendor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "order_process")
    private String orderProcess;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "vendor_id")
    private String vendorId;

    public Setting() {
    }

    public Setting(Integer id) {
        this.id = id;
    }

    public Setting(Integer id, String sheetId, String sheetTab, int startRow, String staffName, String vendor, String shippingName, String shippingLastname, String address1, String address2, String city, String state, String zip, String phone, String toBuy, String payToVendor, String orderProcess, String vendorId) {
        this.id = id;
        this.sheetId = sheetId;
        this.sheetTab = sheetTab;
        this.startRow = startRow;
        this.staffName = staffName;
        this.vendor = vendor;
        this.shippingName = shippingName;
        this.shippingLastname = shippingLastname;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.toBuy = toBuy;
        this.payToVendor = payToVendor;
        this.orderProcess = orderProcess;
        this.vendorId = vendorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getSheetTab() {
        return sheetTab;
    }

    public void setSheetTab(String sheetTab) {
        this.sheetTab = sheetTab;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingLastname() {
        return shippingLastname;
    }

    public void setShippingLastname(String shippingLastname) {
        this.shippingLastname = shippingLastname;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToBuy() {
        return toBuy;
    }

    public void setToBuy(String toBuy) {
        this.toBuy = toBuy;
    }

    public String getPayToVendor() {
        return payToVendor;
    }

    public void setPayToVendor(String payToVendor) {
        this.payToVendor = payToVendor;
    }

    public String getOrderProcess() {
        return orderProcess;
    }

    public void setOrderProcess(String orderProcess) {
        this.orderProcess = orderProcess;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
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
        if (!(object instanceof Setting)) {
            return false;
        }
        Setting other = (Setting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.thaisoftplus.aup.jpa.entity.Setting[ id=" + id + " ]";
    }
    
}
