/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.page.amazon;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.exception.CannotOrderException;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import com.thaisoftplus.aup.page.BasePage;
import com.thaisoftplus.aup.util.PageHelper;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

/**
 *
 * @author witta
 */
public class Checkout extends BasePage implements Serializable {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Checkout.class);
    private static final String USE_THIS_ADDRESS_BUTTON_XPATH = "*//span[@id='orderSummaryPrimaryActionBtn']//input[@type='submit']";
    private static final String USE_THIS_BILL_ADDRESS_BUTTON_XPATH = "*//span[@id='shipToThisAddressButton']//input[@type='submit']";
    private static final String USE_THIS_ADDRESS_TEXT_XPATH = "*//span[@id='orderSummaryPrimaryActionBtn-announce']";
    private static final String USE_THIS_PAYMENT_METHOD_BUTTON_XPATH = "*//span[@id='useThisPaymentMethodButtonId']//input[@type='submit']";
    private static final String PLACE_YOUR_ORDER_BUTTON_XPATH = "*//span[@id='bottomSubmitOrderButtonId']//input[@type='submit']";
    private static final String PLACE_YOUR_ORDER_TOP_BUTTON_XPATH = "*//span[@id='submitOrderButtonId']//input[@type='submit']";
    private static final String ORDER_TOTAL_XPATH = "*//td[contains(@class, 'grand-total-price')]";

    private final SheetManagement sheetManagement;
    private Login login;

    public Checkout(WebDriver driver, Login login) {
        super(driver);
        this.sheetManagement = SheetManagement.getInstance();
        this.login = login;
    }

    public void performPay() throws IOException, CannotOrderException {
        if(driver.getCurrentUrl().contains("signin")){
            this.login.reLogin();
        }
        String useThisAddressText = getUseThisAddressText();
        String orderTotalText = getOrderTotalText();
        clickUseThisAddress();
        PageHelper.waitForTextChanged(driver, By.xpath(USE_THIS_ADDRESS_TEXT_XPATH), useThisAddressText);
        PageHelper.waitForTextChanged(driver, By.xpath(ORDER_TOTAL_XPATH), orderTotalText);
        sheetManagement.setPrice(new String[]{getOrderTotalText()});
        String orderCommand = sheetManagement.getDataInColumn(ApplicationContext.ORDER_PROCESS_COLUMN);
        if ("order".equals(orderCommand)) {
            clickUsePaymentMethod();
            clickUseThisBillAddress();
            clickPlaceYourOrder();
        } else {
            String orderUrl = sheetManagement.getDataInColumn(ApplicationContext.VENDOR_COLUMN);
            throw new CannotOrderException(orderUrl + ": " + orderCommand);
        }
    }

    private String getUseThisAddressText() throws IOException {
        waitUtilElementClickable(By.xpath(USE_THIS_ADDRESS_TEXT_XPATH));
        return driver.findElement(By.xpath(USE_THIS_ADDRESS_TEXT_XPATH)).getText();
    }

    private String getOrderTotalText() throws IOException {
        return driver.findElement(By.xpath(ORDER_TOTAL_XPATH)).getText();
    }

    private void clickUseThisAddress() throws IOException {
        waitUtilElementClickable(By.xpath(USE_THIS_ADDRESS_BUTTON_XPATH));
        simulatePerformClick(By.xpath(USE_THIS_ADDRESS_BUTTON_XPATH));
    }

    private void clickUsePaymentMethod() throws IOException {
        waitUtilElementExist(By.xpath(USE_THIS_PAYMENT_METHOD_BUTTON_XPATH));
        waitUtilElementClickable(By.xpath(USE_THIS_PAYMENT_METHOD_BUTTON_XPATH));
        simulatePerformClick(By.xpath(USE_THIS_PAYMENT_METHOD_BUTTON_XPATH));
    }

    private void clickUseThisBillAddress() throws IOException {
        waitUtilElementExist(By.xpath(USE_THIS_BILL_ADDRESS_BUTTON_XPATH));
        waitUtilElementClickable(By.xpath(USE_THIS_BILL_ADDRESS_BUTTON_XPATH));
        simulatePerformClick(By.xpath(USE_THIS_BILL_ADDRESS_BUTTON_XPATH));
    }

    private void clickPlaceYourOrder() throws IOException {
        waitUtilElementExist(By.xpath(PLACE_YOUR_ORDER_BUTTON_XPATH));
        waitUtilElementClickable(By.xpath(PLACE_YOUR_ORDER_BUTTON_XPATH));
        waitUtilElementExist(By.xpath(PLACE_YOUR_ORDER_TOP_BUTTON_XPATH));
        waitUtilElementClickable(By.xpath(PLACE_YOUR_ORDER_TOP_BUTTON_XPATH));

        List<WebElement> placeYourOrderButtonElement = driver.findElements(By.xpath(PLACE_YOUR_ORDER_TOP_BUTTON_XPATH));
        while (placeYourOrderButtonElement != null && placeYourOrderButtonElement.size() > 0) {
            try {
                simulatePerformClick(By.xpath(PLACE_YOUR_ORDER_TOP_BUTTON_XPATH));
                Thread.sleep(1000);
                placeYourOrderButtonElement = driver.findElements(By.xpath(PLACE_YOUR_ORDER_TOP_BUTTON_XPATH));
            } catch (InterruptedException ex) {
                logger.error("", ex);
            }
        }

    }
}
