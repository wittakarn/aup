/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.thread;

import com.thaisoftplus.aup.business.GoogleSheetBusiness;
import com.thaisoftplus.aup.context.ApplicationContext;
import static com.thaisoftplus.aup.context.ApplicationContext.sheetSetting;
import com.thaisoftplus.aup.context.SheetContext;
import com.thaisoftplus.aup.domain.ProductData;
import com.thaisoftplus.aup.exception.EnptyRowException;
import com.thaisoftplus.aup.exception.NotRunningException;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import com.thaisoftplus.aup.page.amazon.ProductPage;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
//        options.addArguments("--headless");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }

    @Override
    public void run() {
        try {
            updateAmasonProductData();
            setNextRun(10);
        } catch (NotRunningException ex) {
            logger.info(ex.getMessage());
        } catch (Exception ex) {
            logger.error("", ex);
        }
    }

    private void updateAmasonProductData() throws Exception {
        if (!ApplicationContext.isRunning) {
            throw new NotRunningException("โปรแกรมถูกสั่งระงับการทำงาน");
        }
        GoogleSheetBusiness business = new GoogleSheetBusiness();
        business.updateOldPriceColumn();

        driver = new ChromeDriver(options);
        ProductPage productPage = new ProductPage(driver);
        try {
            productPage.openProductPage();
            List<ProductData> productDatas = productPage.getProductsData();
            business.keepAllProductDetailColumnInCache(productDatas);
            if (SheetManagement.getRowIndex() == SheetContext.endIndexOfBatch) {
                business.updateAllProductDetailColumns();
            }
        } catch (EnptyRowException ere) {
            business.updateAllProductDetailColumns();
            if (sheetSetting.size() - 1 > ApplicationContext.SHEET_INDEX) {
                setNextSheet();
            } else {
                setRerunSheet();
            }
        }

        closeSeleniumBrowser();
    }

    private void closeSeleniumBrowser() {
        driver.quit();
        driver = null;
    }

    private void setNextSheet() {
        if (sheetSetting.size() - 1 > ApplicationContext.SHEET_INDEX) {
            ApplicationContext.SHEET_INDEX += 1;
            SheetContext.startIndexOfBatch = ApplicationContext.START_ROW_INDEX;
            SheetContext.endIndexOfBatch = ApplicationContext.START_ROW_INDEX + SheetContext.CACHE_RANGE - 1;
        }
    }

    private void setRerunSheet() {
        ApplicationContext.SHEET_INDEX = 0;
        SheetContext.startIndexOfBatch = ApplicationContext.START_ROW_INDEX;
        SheetContext.endIndexOfBatch = ApplicationContext.START_ROW_INDEX + SheetContext.CACHE_RANGE - 1;
    }

    private void setNextRun(int waitTime) {
        if (ApplicationContext.isRunning) {

            if (SheetManagement.getRowIndex() == SheetContext.endIndexOfBatch) {
                SheetContext.startIndexOfBatch = SheetContext.endIndexOfBatch + 1;
                SheetContext.endIndexOfBatch = SheetContext.startIndexOfBatch + SheetContext.CACHE_RANGE - 1;
            }

            SheetManagement.updateNextIndex();

            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(new ServiceWorker(), waitTime, TimeUnit.MILLISECONDS);
            scheduler.shutdown();
        }
    }
}
