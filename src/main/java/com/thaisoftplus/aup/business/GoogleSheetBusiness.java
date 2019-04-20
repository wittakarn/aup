/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.business;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.context.SheetContext;
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
        List<List<Object>> newValues = sheetManagement.getDataInColumns(ApplicationContext.NEW_DATA, ApplicationContext.NEW_DATA, SheetContext.startIndexOfBatch, SheetContext.endIndexOfBatch, ApplicationContext.DATA_SHEET_NAME);
        sheetManagement.setDataInColumns(ApplicationContext.OLD_DATA, ApplicationContext.OLD_DATA, SheetContext.startIndexOfBatch, SheetContext.endIndexOfBatch, ApplicationContext.DATA_SHEET_NAME, newValues);
    }

    public void updateAllProductDetailColumn(List<ProductData> productDatas) throws IOException {
        if (productDatas != null) {
            if (productDatas.size() > 0) {
                ProductData productData1 = productDatas.get(0);
                SheetContext.sellerNames1.add(Arrays.asList(productData1.getSellerName()));
                SheetContext.prices1.add(Arrays.asList(productData1.getPrice()));
                SheetContext.shippings1.add(Arrays.asList(productData1.getShipping()));
                SheetContext.addOns1.add(Arrays.asList(productData1.getAddOn()));
                SheetContext.types1.add(Arrays.asList(productData1.getType()));
                SheetContext.deliveries1.add(Arrays.asList(productData1.getDelivery()));
            } else {
                SheetContext.sellerNames1.add(Arrays.asList(""));
                SheetContext.prices1.add(Arrays.asList(""));
                SheetContext.shippings1.add(Arrays.asList(""));
                SheetContext.addOns1.add(Arrays.asList(""));
                SheetContext.types1.add(Arrays.asList(""));
                SheetContext.deliveries1.add(Arrays.asList(""));
            }

            if (productDatas.size() > 1) {
                ProductData productData2 = productDatas.get(1);
                SheetContext.sellerNames2.add(Arrays.asList(productData2.getSellerName()));
                SheetContext.prices2.add(Arrays.asList(productData2.getPrice()));
                SheetContext.sellerNames2.add(Arrays.asList(productData2.getShipping()));
                SheetContext.addOns2.add(Arrays.asList(productData2.getAddOn()));
                SheetContext.types2.add(Arrays.asList(productData2.getType()));
                SheetContext.deliveries2.add(Arrays.asList(productData2.getDelivery()));
            } else {
                SheetContext.sellerNames2.add(Arrays.asList(""));
                SheetContext.prices2.add(Arrays.asList(""));
                SheetContext.shippings2.add(Arrays.asList(""));
                SheetContext.addOns2.add(Arrays.asList(""));
                SheetContext.types2.add(Arrays.asList(""));
                SheetContext.deliveries2.add(Arrays.asList(""));
            }

            if (productDatas.size() > 2) {
                ProductData productData3 = productDatas.get(2);
                SheetContext.sellerNames3.add(Arrays.asList(productData3.getSellerName()));
                SheetContext.prices3.add(Arrays.asList(productData3.getPrice()));
                SheetContext.shippings3.add(Arrays.asList(productData3.getShipping()));
                SheetContext.addOns3.add(Arrays.asList(productData3.getAddOn()));
                SheetContext.types3.add(Arrays.asList(productData3.getType()));
                SheetContext.deliveries3.add(Arrays.asList(productData3.getDelivery()));
            } else {
                SheetContext.sellerNames3.add(Arrays.asList(""));
                SheetContext.prices3.add(Arrays.asList(""));
                SheetContext.shippings3.add(Arrays.asList(""));
                SheetContext.addOns3.add(Arrays.asList(""));
                SheetContext.types3.add(Arrays.asList(""));
                SheetContext.deliveries3.add(Arrays.asList(""));
            }
        }

        if (SheetManagement.getRowIndex() == SheetContext.endIndexOfBatch) {
            List<List<Object>> rows = new ArrayList();

            rows.add(SheetContext.sellerNames1);
            rows.add(SheetContext.prices1);
            rows.add(SheetContext.shippings1);
            rows.add(SheetContext.addOns1);
            rows.add(SheetContext.types1);
            rows.add(SheetContext.deliveries1);

            rows.add(SheetContext.sellerNames2);
            rows.add(SheetContext.prices2);
            rows.add(SheetContext.shippings2);
            rows.add(SheetContext.addOns2);
            rows.add(SheetContext.types2);
            rows.add(SheetContext.deliveries2);

            rows.add(SheetContext.sellerNames3);
            rows.add(SheetContext.prices3);
            rows.add(SheetContext.shippings3);
            rows.add(SheetContext.addOns3);
            rows.add(SheetContext.types3);
            rows.add(SheetContext.deliveries3);

            sheetManagement.setDataInColumns(ApplicationContext.SELLER_NAME_1, ApplicationContext.WID_3, SheetContext.startIndexOfBatch, SheetContext.endIndexOfBatch, ApplicationContext.DATA_SHEET_NAME, rows);

            SheetContext.sellerNames1 = new ArrayList();
            SheetContext.prices1 = new ArrayList();
            SheetContext.shippings1 = new ArrayList();
            SheetContext.addOns1 = new ArrayList();
            SheetContext.types1 = new ArrayList();
            SheetContext.deliveries1 = new ArrayList();

            SheetContext.sellerNames2 = new ArrayList();
            SheetContext.prices2 = new ArrayList();
            SheetContext.shippings2 = new ArrayList();
            SheetContext.addOns2 = new ArrayList();
            SheetContext.types2 = new ArrayList();
            SheetContext.deliveries2 = new ArrayList();

            SheetContext.sellerNames3 = new ArrayList();
            SheetContext.prices3 = new ArrayList();
            SheetContext.shippings3 = new ArrayList();
            SheetContext.addOns3 = new ArrayList();
            SheetContext.types3 = new ArrayList();
            SheetContext.deliveries3 = new ArrayList();
        }
    }
}
