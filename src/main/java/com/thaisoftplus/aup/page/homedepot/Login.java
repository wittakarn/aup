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
public class Login extends BasePage implements Serializable {

    private static final String SIGN_IN_INPUT_XPATH = "*//account-logon//input[@id='signinEmail']";
    private static final String PASSWORD_INPUT_XPATH = "*//account-logon//input[@id='password']";
    private static final String LOGIN_BUTTON_XPATH = "*//account-logon//button";

    private Authorization authorization;

    public Login(WebDriver driver, Authorization authorization) {
        super(driver);
        this.authorization = authorization;
    }

    public void performLogin() {
        simulateUserType(By.xpath(SIGN_IN_INPUT_XPATH), authorization.getUserName());
        simulateUserType(By.xpath(PASSWORD_INPUT_XPATH), authorization.getPassword());
        if (waitUtilElementClickable(By.xpath(LOGIN_BUTTON_XPATH))) {
            driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        }
    }
}
