/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.thread;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.exception.NotRunninglException;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import com.thaisoftplus.aup.page.amazon.ProductPage;
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

    private final SheetManagement sheetManagement;
    private final ChromeOptions options;
    private WebDriver driver;

    public ServiceWorker() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        options = new ChromeOptions();
        options.addArguments("user-data-dir=" + ApplicationContext.getUserDataPath());
        options.addArguments("start-maximized");
        options.setHeadless(false);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        sheetManagement = SheetManagement.getInstance();
    }

    @Override
    public void run() {
        try {
            updateAmasonProductData();
        } catch (NotRunninglException ex) {
            logger.info(ex.getMessage());
        } catch (Exception ex) {
            logger.error("", ex);
        }
    }

    private void updateAmasonProductData() throws Exception {
        if (!ApplicationContext.isRunning) {
            throw new NotRunninglException("โปรแกรมถูกสั่งระงับการทำงาน");
        }
        driver = new ChromeDriver(options);

        ProductPage productPage = new ProductPage(driver);
        productPage.openProductPage();
        productPage.getProductsData();
    }

    private void scheduleNextRun(int waitTime) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(new ServiceWorker(), waitTime, TimeUnit.SECONDS);
        scheduler.shutdown();
    }
}
