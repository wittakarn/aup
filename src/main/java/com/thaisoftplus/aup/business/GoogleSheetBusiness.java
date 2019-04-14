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
        if (productDatas.size() > 0) {
            ProductData productData1 = productDatas.get(0);
            sheetManagement.setDataInColumn(ApplicationContext.SELLER_NAME_1, ApplicationContext.DATA_SHEET_NAME, new String[]{productData1.getSellerName()});
            sheetManagement.setDataInColumn(ApplicationContext.PRICE_1, ApplicationContext.DATA_SHEET_NAME, new String[]{productData1.getPrice()});
            sheetManagement.setDataInColumn(ApplicationContext.SHIPING_1, ApplicationContext.DATA_SHEET_NAME, new String[]{productData1.getShipping()});
            sheetManagement.setDataInColumn(ApplicationContext.ADD_ON_1, ApplicationContext.DATA_SHEET_NAME, new String[]{productData1.getAddOn()});
            sheetManagement.setDataInColumn(ApplicationContext.TYPE_1, ApplicationContext.DATA_SHEET_NAME, new String[]{productData1.getType()});
            sheetManagement.setDataInColumn(ApplicationContext.WID_1, ApplicationContext.DATA_SHEET_NAME, new String[]{productData1.getDelivery()});
        }

        if (productDatas.size() > 1) {
            ProductData productData2 = productDatas.get(1);
            sheetManagement.setDataInColumn(ApplicationContext.SELLER_NAME_2, ApplicationContext.DATA_SHEET_NAME, new String[]{productData2.getSellerName()});
            sheetManagement.setDataInColumn(ApplicationContext.PRICE_2, ApplicationContext.DATA_SHEET_NAME, new String[]{productData2.getPrice()});
            sheetManagement.setDataInColumn(ApplicationContext.SHIPING_2, ApplicationContext.DATA_SHEET_NAME, new String[]{productData2.getShipping()});
            sheetManagement.setDataInColumn(ApplicationContext.ADD_ON_2, ApplicationContext.DATA_SHEET_NAME, new String[]{productData2.getAddOn()});
            sheetManagement.setDataInColumn(ApplicationContext.TYPE_2, ApplicationContext.DATA_SHEET_NAME, new String[]{productData2.getType()});
            sheetManagement.setDataInColumn(ApplicationContext.WID_2, ApplicationContext.DATA_SHEET_NAME, new String[]{productData2.getDelivery()});
        }

        if (productDatas.size() > 2) {
            ProductData productData3 = productDatas.get(2);
            sheetManagement.setDataInColumn(ApplicationContext.SELLER_NAME_3, ApplicationContext.DATA_SHEET_NAME, new String[]{productData3.getSellerName()});
            sheetManagement.setDataInColumn(ApplicationContext.PRICE_3, ApplicationContext.DATA_SHEET_NAME, new String[]{productData3.getPrice()});
            sheetManagement.setDataInColumn(ApplicationContext.SHIPING_3, ApplicationContext.DATA_SHEET_NAME, new String[]{productData3.getShipping()});
            sheetManagement.setDataInColumn(ApplicationContext.ADD_ON_3, ApplicationContext.DATA_SHEET_NAME, new String[]{productData3.getAddOn()});
            sheetManagement.setDataInColumn(ApplicationContext.TYPE_3, ApplicationContext.DATA_SHEET_NAME, new String[]{productData3.getType()});
            sheetManagement.setDataInColumn(ApplicationContext.WID_3, ApplicationContext.DATA_SHEET_NAME, new String[]{productData3.getDelivery()});
        }
    }
}
