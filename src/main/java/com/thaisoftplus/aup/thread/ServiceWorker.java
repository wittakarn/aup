/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.thread;

import com.thaisoftplus.aup.business.GoogleSheetBusiness;
import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.context.SheetContext;
import com.thaisoftplus.aup.domain.ProductData;
import com.thaisoftplus.aup.exception.EnptyRowException;
import com.thaisoftplus.aup.exception.NotRunningException;
import com.thaisoftplus.aup.page.amazon.ProductPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wittakarn
 */
public class ServiceWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ServiceWorker.class);
    private final ChromeOptions options;
    private WebDriver driver;

    public ServiceWorker() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        options = new ChromeOptions();
        options.addArguments("user-data-dir=" + ApplicationContext.getUserDataPath());
        options.addArguments("start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }

    @Override
    public void run() {
        try {
            updateAmasonProductData();
        } catch (NotRunningException | EnptyRowException ex) {
            logger.info(ex.getMessage());
            SheetContext.isDone = true;
        } catch (Exception ex) {
            logger.error("", ex);
        }
    }

    private void updateAmasonProductData() throws Exception {
        GoogleSheetBusiness business = new GoogleSheetBusiness();

        driver = new ChromeDriver(options);
        ProductPage productPage = new ProductPage(driver);
        int currentIndex = productPage.openProductPage();
        List<ProductData> productDatas = productPage.getProductsData();
        business.keepAllProductDetailColumnInCache(currentIndex, productDatas);

        closeSeleniumBrowser();
        if (SheetContext.urls != null && SheetContext.urls.size() > 0) {
            updateAmasonProductData();
        }
    }

    private void closeSeleniumBrowser() {
        driver.quit();
        driver = null;
    }
}
