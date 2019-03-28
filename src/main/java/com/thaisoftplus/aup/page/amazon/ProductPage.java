/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.page.amazon;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import com.thaisoftplus.aup.page.BasePage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Administrator
 */
public class ProductPage extends BasePage implements Serializable {

    private static final String BUY_OPTIONS_LIST_XPATH = "*//div[@id='olpOfferList']";
    private static final String BUY_OPTIONS_XPATH = BUY_OPTIONS_LIST_XPATH + "//div[contains(@class ,'olpOffer')]";
    
    private static final String PRICE_XPATH = "*//span[contains(@class ,'olpOfferPrice')]";
    private static final String SELLER_NAME_XPATH = "*//h3[contains(@class ,'olpSellerName')]";
    private static final String SHIPPING_PRICE_XPATH = "*//span[@class='olpShippingPrice']";
    private static final String ADD_ON_XPATH = "*//a[contains(@class ,'a-icon-addon')]";
    private static final String PRIME_XPATH = "*//span[contains(@class ,'supersaver')]";
    private static final String DELIVERY_XPATH = "*//ul[contains(@class ,'olpFastTrack')]";
    
    private static final int MAX_OPTION_SIZE = 3;
    private final SheetManagement sheetManagement;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.sheetManagement = SheetManagement.getInstance();
    }

    public void getProductsData() {
        List<WebElement> productOptionElements = getProductOption();
        WebElement priceElement = null;
        WebElement sellerNameElement = null;
        WebElement shippingPriceElement = null;
        WebElement addOnElement = null;
        WebElement primeElement = null;
        WebElement deliveryElement = null;
        if (productOptionElements != null) {
            for (WebElement productOptionElement : productOptionElements) {
                priceElement = getFirstElement(productOptionElement, By.xpath(PRICE_XPATH));
                sellerNameElement = getFirstElement(productOptionElement, By.xpath(SELLER_NAME_XPATH));
                shippingPriceElement = getFirstElement(productOptionElement, By.xpath(SHIPPING_PRICE_XPATH));
                addOnElement = getFirstElement(productOptionElement, By.xpath(ADD_ON_XPATH));
                primeElement = getFirstElement(productOptionElement, By.xpath(PRIME_XPATH));
                deliveryElement = getFirstElement(productOptionElement, By.xpath(DELIVERY_XPATH));
                
                System.out.println("priceElement: " + getDefaultText(priceElement));
                System.out.println("sellerNameElement: " + getDefaultText(sellerNameElement));
                System.out.println("shippingPriceElement: " + getDefaultText(shippingPriceElement));
                System.out.println("addOnElement: " + getDefaultText(addOnElement));
                System.out.println("primeElement: " + getDefaultText(primeElement));
                System.out.println("deliveryElement: " + getDefaultText(deliveryElement));
            }
        }
    }

    private List<WebElement> getProductOption() {
        List<WebElement> optionElements = null;
        if (waitUtilElementVisible(By.xpath(BUY_OPTIONS_XPATH))) {
            List<WebElement> webElements = driver.findElements(By.xpath(BUY_OPTIONS_XPATH));
            if (webElements != null && webElements.size() > 0) {
                optionElements = new ArrayList<WebElement>();
                int optionsSize = webElements.size();
                optionsSize = optionsSize > MAX_OPTION_SIZE ? MAX_OPTION_SIZE : optionsSize;
                for (int i = 0; i < optionsSize; i++) {
                    optionElements.add(webElements.get(i));
                }
            }
        }
        return optionElements;
    }
}
