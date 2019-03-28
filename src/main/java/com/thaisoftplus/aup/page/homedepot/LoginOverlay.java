/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.page.homedepot;

import com.thaisoftplus.aup.domain.Authorization;
import com.thaisoftplus.aup.page.BasePage;
import java.io.Serializable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Administrator
 */
public class LoginOverlay extends BasePage implements Serializable {

    private static final String SIGN_IN_ICON_XPATH = "*//a[@id='headerMyAccount']";
    private static final String SIGN_IN_OVERLAY_XPATH = "*//div[@id='headerMyAccountDropdown']";
    private static final String SIGN_IN_BUTTON_XPATH = SIGN_IN_OVERLAY_XPATH + "//a[@data-action='signIn']";
    private static final String SIGN_IN_POPUP_XPATH = "*//div[@id='authenticationDualSignin']";
    private static final String SIGN_IN_INPUT_XPATH = SIGN_IN_POPUP_XPATH + "//input[@id='email_id']";
    private static final String PASSWORD_INPUT_XPATH = SIGN_IN_POPUP_XPATH + "//input[@id='password']";
    private static final String LOGIN_BUTTON_XPATH = SIGN_IN_POPUP_XPATH + "//button[@id='accountSignIn']";

    private Authorization authorization;

    public LoginOverlay(WebDriver driver, Authorization authorization) {
        super(driver);
        this.authorization = authorization;
    }

    public void performLogin() {
        waitUtilElementClickable(By.xpath(SIGN_IN_ICON_XPATH));
        driver.findElement(By.xpath(SIGN_IN_ICON_XPATH)).click();
        waitUtilElementVisible(By.xpath(SIGN_IN_OVERLAY_XPATH));
        waitUtilElementClickable(By.xpath(SIGN_IN_BUTTON_XPATH));
        driver.findElement(By.xpath(SIGN_IN_BUTTON_XPATH)).click();
        waitUtilElementVisible(By.xpath(SIGN_IN_POPUP_XPATH));
        
        simulateUserType(By.xpath(SIGN_IN_INPUT_XPATH), authorization.getUserName());
        simulateUserType(By.xpath(PASSWORD_INPUT_XPATH), authorization.getPassword());
        waitUtilElementClickable(By.xpath(LOGIN_BUTTON_XPATH));
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
    }
}
