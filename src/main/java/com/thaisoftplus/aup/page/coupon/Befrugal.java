/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.page.coupon;

import com.thaisoftplus.aup.page.BasePage;
import java.io.Serializable;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author witta
 */
public class Befrugal extends BasePage implements Serializable {

    private static final String LOGIN_LINK_XPATH = "*//li[@id='ctl00_hdr_hpi_liLogIn']";
    private static final String MY_ACCOUNT_LINK_XPATH = "*//li[@id='ctl00_hdr_hpi_liMyAccount']//a";
    private static final String LOGIN_POPUP_XPATH = "*//div[@id='ctl00_hdr_bfLoginStatus_pnlLogin']";
    private static final String SIGN_IN_INPUT_XPATH = "*//input[@id='ctl00_hdr_bfLoginStatus_LoginControl_txtUser']";
    private static final String PASSWORD_INPUT_XPATH = "*//input[@id='ctl00_hdr_bfLoginStatus_LoginControl_txtPass']";
    private static final String LOGIN_BUTTON_XPATH = "*//input[@id='ctl00_hdr_bfLoginStatus_LoginControl_btnLogin']";
    private static final String SHOP_NOW_BUTTON_XPATH = "*//td[@class='stores-shop-now']//a";

    public Befrugal(WebDriver driver) {
        super(driver);
    }

    public void applyCoupon(String url, String user, String password) {
        driver.navigate().to(url);
        waitUtilPageLoad();
        performLogin(user, password);
        waitUtilPageLoad();
        clickShopNow();
        switchToNewTab();
        waitRedirect();
        waitUtilPageLoad();
    }

    private void performLogin(String user, String password) {
        String currentUrl = driver.getCurrentUrl();
        if (isElementExist(By.xpath(MY_ACCOUNT_LINK_XPATH))) {
            return;
        }
        if (waitUtilElementClickable(By.xpath(LOGIN_LINK_XPATH))) {
            driver.findElement(By.xpath(LOGIN_LINK_XPATH)).click();
        }
        waitUtilElementVisible(By.xpath(LOGIN_POPUP_XPATH));
        simulateUserType(By.xpath(SIGN_IN_INPUT_XPATH), user);

        if (waitUtilElementClickable(By.xpath(LOGIN_BUTTON_XPATH))) {
            driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        }

        waitUtilElementVisible(By.xpath(PASSWORD_INPUT_XPATH));
        simulateUserType(By.xpath(PASSWORD_INPUT_XPATH), password);

        if (waitUtilElementClickable(By.xpath(LOGIN_BUTTON_XPATH))) {
            driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
            waitUtilUrlChanged(currentUrl);
        }
    }

    private void clickShopNow() {
        waitUtilElementClickable(By.xpath(SHOP_NOW_BUTTON_XPATH));
        driver.findElement(By.xpath(SHOP_NOW_BUTTON_XPATH)).click();
    }

    private void waitRedirect() {
        String currentUrl = driver.getCurrentUrl();
        waitUtilUrlChanged(currentUrl);
    }

    private void switchToNewTab() {
        waitForNumberOfWindowsToEqual(2);
        Set<String> windows = driver.getWindowHandles();
        if (windows != null && windows.size() > 1) {
            driver.switchTo().window(windows.toArray()[1].toString());
        }
    }
}
