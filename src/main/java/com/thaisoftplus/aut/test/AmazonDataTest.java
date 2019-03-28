/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aut.test;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import com.thaisoftplus.aup.page.amazon.ProductPage;
import com.thaisoftplus.aup.util.PageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.LoggerFactory;

/**
 *
 * @author witta
 */
public class AmazonDataTest {
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AmazonDataTest.class);
    private static SheetManagement sheetManagement;
    private static ChromeOptions options;
    private static WebDriver driver;
    
    public static void main(String[] args) {
        try {
            String chromeDriverPath = "D:\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            options = new ChromeOptions();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            options.addArguments("user-data-dir=" + ApplicationContext.getUserDataPath());
            options.addArguments("start-maximized");
            options.setHeadless(false);
            
            sheetManagement = SheetManagement.getInstance();
            fetchData();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void fetchData() throws Exception {
        driver = new ChromeDriver(options);
        String productUrl = "https://www.amazon.com/gp/offer-listing/B00005C5H4/ref=dp_olp_all_mbc?ie=UTF8&condition=all";
        driver.get(productUrl);
        PageHelper.waitUtilPageLoad(driver);
        ProductPage productPage = new ProductPage(driver);
        productPage.getProductsData();
    }
}
