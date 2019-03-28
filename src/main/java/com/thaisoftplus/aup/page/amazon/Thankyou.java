/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.page.amazon;

import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import com.thaisoftplus.aup.page.BasePage;
import java.io.IOException;
import java.io.Serializable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author witta
 */
public class Thankyou extends BasePage implements Serializable{
    private static final String ORDER_NUMBER_XPATH = "*//span[contains(@id, 'order-number')]";
    private final SheetManagement sheetManagement;

    public Thankyou(WebDriver driver) {
        super(driver);
        this.sheetManagement = SheetManagement.getInstance();
    }
    
    public void stampOrderNumber() throws IOException{
        waitUtilElementExist(By.xpath(ORDER_NUMBER_XPATH));
        String orderName = driver.findElement(By.xpath(ORDER_NUMBER_XPATH)).getText();
        
        sheetManagement.setStaffName(new String[] {"FullFilled Software"});
        sheetManagement.setVendorId(new String[] {orderName.trim()});
    }
}
