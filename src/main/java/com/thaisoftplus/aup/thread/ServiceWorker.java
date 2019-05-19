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

    public ServiceWorker(int threadNumber) {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems(linux)
        options.addArguments("user-data-dir=" + ApplicationContext.getUserDataPath() + threadNumber);
        options.addArguments("start-maximized");
        
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
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
            logger.error("", ex);
        } finally {
            closeSeleniumBrowser();
        }
    }

    private void updateAmasonProductData() throws BatchEndException, EnptyRowException {
        if (ApplicationContext.isRunning) {
            GoogleSheetBusiness business = new GoogleSheetBusiness();

            driver = new ChromeDriver(options);
            ProductPage productPage = new ProductPage(driver);
            int currentIndex = productPage.openProductPage();
            List<ProductData> productDatas = productPage.getProductsData();
            business.keepAllProductDetailColumnInCache(currentIndex, productDatas);

            closeSeleniumBrowser();
            if (!SheetContext.isUrlsEmpty()) {
                updateAmasonProductData();
            }
        }
    }

    private void closeSeleniumBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
