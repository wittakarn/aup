/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.page.amazon;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.domain.ProductData;
import com.thaisoftplus.aup.exception.EnptyRowException;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import com.thaisoftplus.aup.page.BasePage;
import com.thaisoftplus.aup.util.PageHelper;
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
    private static final String ADD_ON_XPATH = "*//*[contains(@class ,'a-icon-addon')]";
    private static final String PRIME_XPATH = "*//span[contains(@class ,'supersaver')]";
    private static final String DELIVERY_XPATH = "*//ul[contains(@class ,'olpFastTrack')]";

    private static final int MAX_OPTION_SIZE = 3;
    private final SheetManagement sheetManagement;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.sheetManagement = SheetManagement.getInstance();
    }

    public void openProductPage() throws IOException, EnptyRowException {
        String url = this.sheetManagement.getDataInColumn(ApplicationContext.LINK, ApplicationContext.DATA_SHEET_NAME);
        if (url == null || url.trim() == "") {
            throw new EnptyRowException("URL in next row is " + url);
        }

        driver.get(url);
        PageHelper.waitUtilPageLoad(driver);
    }

    public List<ProductData> getProductsData() {
        List<ProductData> pds = null;
        WebElement priceElement;
        WebElement sellerNameElement;
        WebElement shippingPriceElement;
        WebElement addOnElement;
        WebElement primeElement;
        WebElement deliveryElement;
        List<WebElement> productOptionElements = getProductOption();
        if (productOptionElements != null) {
            pds = new ArrayList<>();
            for (WebElement productOptionElement : productOptionElements) {
                ProductData pd = new ProductData();
                priceElement = getFirstElement(productOptionElement, By.xpath(PRICE_XPATH));
                sellerNameElement = getFirstElement(productOptionElement, By.xpath(SELLER_NAME_XPATH));
                shippingPriceElement = getFirstElement(productOptionElement, By.xpath(SHIPPING_PRICE_XPATH));
                addOnElement = getFirstElement(productOptionElement, By.xpath(ADD_ON_XPATH));
                primeElement = getFirstElement(productOptionElement, By.xpath(PRIME_XPATH));
                deliveryElement = getFirstElement(productOptionElement, By.xpath(DELIVERY_XPATH));

                pd.setPrice(getDefaultText(priceElement).replace("$", ""));

                String sellerName = getDefaultText(sellerNameElement);
                if (sellerName.equals("")) {
                    List<WebElement> images = sellerNameElement.findElements(By.xpath("//img"));
                    if (images != null && images.size() > 0) {
                        pd.setSellerName("amazon");
                    }
                } else {
                    pd.setSellerName(sellerName);
                }

                pd.setShipping(getDefaultText(shippingPriceElement).replace("$", ""));

                if (addOnElement != null) {
                    pd.setAddOn("Add-on");
                }
                if (primeElement != null) {
                    pd.setType("prime");
                }
                if (getDefaultText(deliveryElement).toLowerCase().contains("want it deliver")) {
                    pd.setDelivery("WID");
                }
                pds.add(pd);
            }
        }
        return pds;
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
