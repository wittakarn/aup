/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.business;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.context.SheetContext;
import com.thaisoftplus.aup.domain.ProductData;
import com.thaisoftplus.aup.domain.SellerData;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author witta
 */
public class GoogleSheetBusiness {
    
    private static final Logger logger = LoggerFactory.getLogger(GoogleSheetBusiness.class);
    private final SheetManagement sheetManagement;
    
    public GoogleSheetBusiness() {
        this.sheetManagement = SheetManagement.getInstance();
    }
    
    public void updateOldPriceColumn() throws IOException {
        List<List<Object>> rows = new ArrayList();
        List<Object> cells = convert2DListToList(sheetManagement.getDataInColumns(ApplicationContext.NEW_DATA, ApplicationContext.NEW_DATA, SheetContext.startIndexOfBatch, SheetContext.endIndexOfBatch, ApplicationContext.DATA_SHEET_NAME));
        rows.add(cells);
        
        sheetManagement.setDataInColumns(ApplicationContext.OLD_DATA, ApplicationContext.OLD_DATA, SheetContext.startIndexOfBatch, SheetContext.endIndexOfBatch, ApplicationContext.DATA_SHEET_NAME, rows);
    }
    
    public void keepAllProductDetailColumnInCache(int currentIndex, List<ProductData> productDatas) throws IOException {
        SellerData sellersData = new SellerData();
        if (productDatas.size() > 0) {
            ProductData productData1 = productDatas.get(0);
            sellersData.setProductData1(productData1);
        } else {
            sellersData.setProductData1(new ProductData());
        }
        
        if (productDatas.size() > 1) {
            ProductData productData2 = productDatas.get(1);
            sellersData.setProductData2(productData2);
        } else {
            sellersData.setProductData2(new ProductData());
        }
        
        if (productDatas.size() > 2) {
            ProductData productData3 = productDatas.get(2);
            sellersData.setProductData3(productData3);
        } else {
            sellersData.setProductData3(new ProductData());
        }
        SheetContext.sellersData.add(sellersData);
    }
    
    public void updateAllProductDetailColumns() throws IOException {
        Collections.sort(SheetContext.sellersData);
        if (SheetContext.sellersData.size() > 0) {
            List<List<Object>> rows = new ArrayList();
            
            List<Object> sellerNames1 = new ArrayList();
            List<Object> prices1 = new ArrayList();
            List<Object> shippings1 = new ArrayList();
            List<Object> addOns1 = new ArrayList();
            List<Object> types1 = new ArrayList();
            List<Object> deliveries1 = new ArrayList();
            
            List<Object> sellerNames2 = new ArrayList();
            List<Object> prices2 = new ArrayList();
            List<Object> shippings2 = new ArrayList();
            List<Object> addOns2 = new ArrayList();
            List<Object> types2 = new ArrayList();
            List<Object> deliveries2 = new ArrayList();
            
            List<Object> sellerNames3 = new ArrayList();
            List<Object> prices3 = new ArrayList();
            List<Object> shippings3 = new ArrayList();
            List<Object> addOns3 = new ArrayList();
            List<Object> types3 = new ArrayList();
            List<Object> deliveries3 = new ArrayList();
            
            for (SellerData sellerData : SheetContext.sellersData) {
                sellerNames1.add(sellerData.getProductData1().getSellerName());
                prices1.add(sellerData.getProductData1().getPrice());
                shippings1.add(sellerData.getProductData1().getShipping());
                addOns1.add(sellerData.getProductData1().getAddOn());
                types1.add(sellerData.getProductData1().getType());
                deliveries1.add(sellerData.getProductData1().getDelivery());
                
                sellerNames2.add(sellerData.getProductData2().getSellerName());
                prices2.add(sellerData.getProductData2().getPrice());
                shippings2.add(sellerData.getProductData2().getShipping());
                addOns2.add(sellerData.getProductData2().getAddOn());
                types2.add(sellerData.getProductData2().getType());
                deliveries2.add(sellerData.getProductData2().getDelivery());
                
                sellerNames3.add(sellerData.getProductData3().getSellerName());
                prices3.add(sellerData.getProductData3().getPrice());
                shippings3.add(sellerData.getProductData3().getShipping());
                addOns3.add(sellerData.getProductData3().getAddOn());
                types3.add(sellerData.getProductData3().getType());
                deliveries3.add(sellerData.getProductData3().getDelivery());
            }
            
            rows.add(sellerNames1);
            rows.add(prices1);
            rows.add(shippings1);
            rows.add(addOns1);
            rows.add(types1);
            rows.add(deliveries1);
            
            rows.add(sellerNames2);
            rows.add(prices2);
            rows.add(shippings2);
            rows.add(addOns2);
            rows.add(types2);
            rows.add(deliveries2);
            
            rows.add(sellerNames3);
            rows.add(prices3);
            rows.add(shippings3);
            rows.add(addOns3);
            rows.add(types3);
            rows.add(deliveries3);
            
            try {
                sheetManagement.setDataInColumns(ApplicationContext.SELLER_NAME_1, ApplicationContext.WID_3, SheetContext.startIndexOfBatch, SheetContext.currentIdex, ApplicationContext.DATA_SHEET_NAME, rows);
            } catch (IOException ex) {
                // retry once
                sheetManagement.setDataInColumns(ApplicationContext.SELLER_NAME_1, ApplicationContext.WID_3, SheetContext.startIndexOfBatch, SheetContext.currentIdex, ApplicationContext.DATA_SHEET_NAME, rows);
            }
            
            SheetContext.sellersData = Collections.synchronizedList(new ArrayList());
        }
    }
    
    public static List<Object> convert2DListToList(List<List<Object>> rows) {
        List<Object> cells = new ArrayList();
        for (List<Object> row : rows) {
            for (Object cell : row) {
                cells.add(cell);
            }
        }
        return cells;
    }
    
    public static Queue<Object> convert2DListToQueue(List<List<Object>> rows) {
        Queue queue = new ConcurrentLinkedQueue();
        for (List<Object> row : rows) {
            for (Object cell : row) {
                queue.add(cell);
            }
        }
        return queue;
    }
}
