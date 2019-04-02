/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.thread;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.domain.Authorization;
import com.thaisoftplus.aup.exception.CannotOrderException;
import com.thaisoftplus.aup.exception.NoOrderException;
import com.thaisoftplus.aup.exception.NotRunninglException;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import com.thaisoftplus.aup.page.PageUrl;
import com.thaisoftplus.aup.page.amazon.Address;
import com.thaisoftplus.aup.page.amazon.Checkout;
import com.thaisoftplus.aup.page.amazon.Coupon;
import com.thaisoftplus.aup.page.amazon.Login;
import com.thaisoftplus.aup.page.amazon.ProductPage;
import com.thaisoftplus.aup.page.amazon.ShoppingCart;
import com.thaisoftplus.aup.page.amazon.Thankyou;
import com.thaisoftplus.aup.page.coupon.Befrugal;
import com.thaisoftplus.aup.util.PageHelper;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
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
        boolean needRunImmediately = true;
        boolean needClearCart = true;
        try {
            orderProduct();
        } catch (NoOrderException ex) {
            logger.info(ex.getMessage());
            scheduleNextRun(900);
            needClearCart = false;
            needRunImmediately = false;
        } catch (NotRunninglException ex) {
            needRunImmediately = false;
            logger.info(ex.getMessage());
        } catch (CannotOrderException ex) {
            logger.info(ex.getMessage());
            try {
                setUnableOrder();
            } catch (IOException ioEx) {
                logger.error("", ioEx);
            }
        } catch (Exception ex) {
            logger.error("", ex);
        }
        try {
            if (needClearCart) {
                clear();
            }
            if (needRunImmediately) {
                scheduleNextRun(1);
            }
        } catch (Exception ex) {
            logger.error("", ex);
        }
    }

    private void orderProduct() throws Exception {
        if (!ApplicationContext.isRunning) {
            throw new NotRunninglException("โปรแกรมถูกสั่งระงับการทำงาน");
        }

        String productUrl = sheetManagement.getDataInColumn(ApplicationContext.VENDOR_COLUMN);
        logger.debug("productUrl = " + productUrl);
        if ("".equals(productUrl)) {
            throw new NoOrderException("ไม่มีสินค้าที่ต้องสั่งหลงเหลืออยู่");
        }

        String orderCommand = sheetManagement.getDataInColumn(ApplicationContext.STAFF_NAME_COLUMN);
        logger.debug("orderCommand = " + orderCommand);
        if (!"".equals(orderCommand) || !productUrl.contains("https://www.amazon.com")) {
            sheetManagement.updateNextIndex();
            orderProduct();
            return;
        }
        driver = new ChromeDriver(options);
        driver.get(PageUrl.AMAZON_HOME);
        Login login = new Login(driver, new Authorization(ApplicationContext.AMAZON_USER, ApplicationContext.AMAZON_PASSWORD));
        login.performLogin();
        PageHelper.waitUtilPageLoad(driver);

        Address address = new Address(driver, login);
        address.setShippingAddress();

        driver.navigate().to(productUrl);
        PageHelper.waitUtilPageLoad(driver);
        ProductPage productPage = new ProductPage(driver);

        ShoppingCart shoppingCart = new ShoppingCart(driver);
        shoppingCart.PerformCheckout();
        PageHelper.waitUtilPageLoad(driver);

        Checkout checkout = new Checkout(driver, login);
        checkout.performPay();

        PageHelper.waitUtilPageLoad(driver);
        Thankyou thankyou = new Thankyou(driver);
        thankyou.stampOrderNumber();
    }

    private void clear() throws IOException {
        ShoppingCart shoppingCart = new ShoppingCart(driver);
        shoppingCart.clearAllCart();
        sheetManagement.updateNextIndex();
        driver.quit();
        driver = null;
    }

    private void scheduleNextRun(int waitTime) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(new ServiceWorker(), waitTime, TimeUnit.SECONDS);
        scheduler.shutdown();
    }

    private void setUnableOrder() throws IOException{
        sheetManagement.setStaffName(new String[]{"NSA/PTH"});
        sheetManagement.setVendorId(new String[]{"NSA/PTH"});
    }
}
