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
import com.thaisoftplus.aup.exception.BatchEndException;
import com.thaisoftplus.aup.exception.EnptyRowException;
import com.thaisoftplus.aup.page.amazon.ProductPage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wittakarn
 */
public class ServiceWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ServiceWorker.class);
    private final FirefoxOptions options;
    private WebDriver driver;

    public ServiceWorker(int threadNumber) {
        FirefoxProfile customProfile = new FirefoxProfile(new File(ApplicationContext.getBrowserProfilePath() + threadNumber));
        options = new FirefoxOptions();

        options.setHeadless(true);
        options.setBinary(ApplicationContext.getBrowserBinaryPath());
        options.setProfile(customProfile);

        driver = new FirefoxDriver(options);
    }

    @Override
    public void run() {
        try {
            updateAmasonProductData();
        } catch (BatchEndException ex) {
            logger.info(ex.getMessage());
        } catch (EnptyRowException ex) {
            SheetContext.isDone = true;
            logger.info(ex.getMessage());
        } catch (Exception ex) {
            logger.error("unexpected error", ex);
        } finally {
            quitSeleniumBrowser();
        }
    }

    private void updateAmasonProductData() throws BatchEndException, EnptyRowException {
        if (ApplicationContext.isRunning) {

            GoogleSheetBusiness business = new GoogleSheetBusiness();

            ProductPage productPage = new ProductPage(driver);
            int currentIndex = productPage.openProductPage();
            
            List<ProductData> productDatas = null;
            try {
                productDatas = productPage.getProductsData();
            } catch (Exception ex) {
                logger.error("getProductsData error", ex);
                productDatas = new ArrayList<>();
            }

            business.keepAllProductDetailColumnInCache(currentIndex, productDatas);

            if (!SheetContext.isUrlsEmpty()) {
                updateAmasonProductData();
            }
        }
    }

    private void quitSeleniumBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
