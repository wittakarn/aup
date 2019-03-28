/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.page.homedepot;

import com.thaisoftplus.aup.page.BasePage;
import java.io.Serializable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Administrator
 */
public class PageContent extends BasePage implements Serializable{
    private static final String PRICE = "*//*[@id='ajaxPrice']";
    
    public PageContent(WebDriver driver) {
        super(driver);
    }
    
    public String getProductPrice() {
        if (waitUtilElementVisible(By.xpath(PRICE))) {
            WebElement webElement = driver.findElement(By.xpath(PRICE));
            if(webElement != null){
                return webElement.getAttribute("content");
            }
        }
        return null;
    }
}
