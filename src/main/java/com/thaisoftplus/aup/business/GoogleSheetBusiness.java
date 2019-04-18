/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.business;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.domain.ProductData;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author witta
 */
public class GoogleSheetBusiness {

    private final SheetManagement sheetManagement;

    public GoogleSheetBusiness() {
        this.sheetManagement = SheetManagement.getInstance();
    }

    public void updateOldPriceColumn() throws IOException {
        String newValue = sheetManagement.getDataInColumn(ApplicationContext.NEW_DATA, ApplicationContext.DATA_SHEET_NAME);
        sheetManagement.setDataInColumn(ApplicationContext.OLD_DATA, ApplicationContext.DATA_SHEET_NAME, new String[]{newValue});
    }

    public void updateAllProductDetailColumn(List<ProductData> productDatas) throws IOException {
        List<List<Object>> rows = new ArrayList();
        
//        for (int i = 0; i < 18; i++) {
//            rows.add(new ArrayList());
//        }
//        rows.get(0).add
        if (productDatas != null) {
            if (productDatas.size() > 0) {
                ProductData productData1 = productDatas.get(0);
                rows.add(Arrays.asList(productData1.getSellerName()));
                rows.add(Arrays.asList(productData1.getPrice()));
                rows.add(Arrays.asList(productData1.getShipping()));
                rows.add(Arrays.asList(productData1.getAddOn()));
                rows.add(Arrays.asList(productData1.getType()));
                rows.add(Arrays.asList(productData1.getDelivery()));
            }

            if (productDatas.size() > 1) {
                ProductData productData2 = productDatas.get(1);
                rows.add(Arrays.asList(productData2.getSellerName()));
                rows.add(Arrays.asList(productData2.getPrice()));
                rows.add(Arrays.asList(productData2.getShipping()));
                rows.add(Arrays.asList(productData2.getAddOn()));
                rows.add(Arrays.asList(productData2.getType()));
                rows.add(Arrays.asList(productData2.getDelivery()));
            }

            if (productDatas.size() > 2) {
                ProductData productData3 = productDatas.get(2);
                rows.add(Arrays.asList(productData3.getSellerName()));
                rows.add(Arrays.asList(productData3.getPrice()));
                rows.add(Arrays.asList(productData3.getShipping()));
                rows.add(Arrays.asList(productData3.getAddOn()));
                rows.add(Arrays.asList(productData3.getType()));
                rows.add(Arrays.asList(productData3.getDelivery()));
            }
        }

        if (rows.size() > 0) {
            sheetManagement.setDataInColumn(ApplicationContext.SELLER_NAME_1, ApplicationContext.WID_3, ApplicationContext.DATA_SHEET_NAME, rows);
        }

        for (int i = rows.size(); i < 3; i++) {
            clearDetailColumn(rows);
        }
    }

    public void clearDetailColumn(List<List<Object>> rows) throws IOException {
        rows.add(Arrays.asList(""));
        rows.add(Arrays.asList(""));
        rows.add(Arrays.asList(""));
        rows.add(Arrays.asList(""));
        rows.add(Arrays.asList(""));
        rows.add(Arrays.asList(""));
    }
}
