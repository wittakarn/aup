/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.page.amazon;

import com.thaisoftplus.aup.domain.Authorization;
import com.thaisoftplus.aup.page.BasePage;
import java.io.Serializable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Administrator
 */
public class LoginPage extends BasePage implements Serializable {

    private static final String LOGIN_LINK_XPATH = "*//a[@id='nav-link-accountList']";
    private static final String SIGN_IN_INPUT_XPATH = "*//input[@id='ap_email']";
    private static final String PASSWORD_INPUT_XPATH = "*//input[@id='ap_password']";
    private static final String LOGIN_BUTTON_XPATH = "*//input[@id='signInSubmit']";

    private Authorization authorization;

    public LoginPage(WebDriver driver, Authorization authorization) {
        super(driver);
        this.authorization = authorization;
    }

    public void performLogin() {
        if (waitUtilElementClickable(By.xpath(LOGIN_LINK_XPATH))) {
            WebElement loginLinkElement = driver.findElement(By.xpath(LOGIN_LINK_XPATH));
            if (loginLinkElement.getAttribute("href").contains("nav_youraccount_btn")) {
                return;
            }
            driver.findElement(By.xpath(LOGIN_LINK_XPATH)).click();
        }
        simulateUserType(By.xpath(SIGN_IN_INPUT_XPATH), authorization.getUserName());
        simulateUserType(By.xpath(PASSWORD_INPUT_XPATH), authorization.getPassword());
        if (waitUtilElementClickable(By.xpath(LOGIN_BUTTON_XPATH))) {
            driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        }
    }
    
    public void reLogin() {
        simulateUserType(By.xpath(PASSWORD_INPUT_XPATH), authorization.getPassword());
        if (waitUtilElementClickable(By.xpath(LOGIN_BUTTON_XPATH))) {
            driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        }
    }
}
