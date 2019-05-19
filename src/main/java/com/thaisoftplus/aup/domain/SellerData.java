/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.domain;

import java.io.Serializable;

/**
 *
 * @author witta
 */
public class SellerData implements Comparable<SellerData>, Serializable {

    private int rowIndex;
    private ProductData productData1;
    private ProductData productData2;
    private ProductData productData3;

    public SellerData() {
    }

    public SellerData(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public ProductData getProductData1() {
        return productData1;
    }

    public void setProductData1(ProductData productData1) {
        this.productData1 = productData1;
    }

    public ProductData getProductData2() {
        return productData2;
    }

    public void setProductData2(ProductData productData2) {
        this.productData2 = productData2;
    }

    public ProductData getProductData3() {
        return productData3;
    }

    public void setProductData3(ProductData productData3) {
        this.productData3 = productData3;
    }

    @Override
    public int compareTo(SellerData o) {
        return this.getRowIndex() - o.getRowIndex();
    }
}
