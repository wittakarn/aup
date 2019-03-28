/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.page.amazon;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import com.thaisoftplus.aup.page.BasePage;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author witta
 */
public class Address extends BasePage implements Serializable {

    private static final String DELETE_ADDRESS_XPATH = "*//a[@id='ya-myab-address-delete-btn-0']";
    private static final String CONFIRM_DELETE_XPATH = "*//span[@id='deleteAddressModal-0-submit-btn']//input[@type='submit']";
    private static final String ADD_ADDRESS_XPATH = "*//a[@id='ya-myab-address-add-link']";
    private static final String COUNTRY_SELECT_XPATH = "*//select[@id='address-ui-widgets-countryCode-dropdown-nativeId']";
    private static final String FULL_NAME_INPUT_XPATH = "*//input[@id='address-ui-widgets-enterAddressFullName']";
    private static final String ADDRESS_LINE1_INPUT_XPATH = "*//input[@id='address-ui-widgets-enterAddressLine1']";
    private static final String ADDRESS_LINE2_INPUT_XPATH = "*//input[@id='address-ui-widgets-enterAddressLine2']";
    private static final String CITY_INPUT_XPATH = "*//input[@id='address-ui-widgets-enterAddressCity']";
    private static final String STATE_INPUT_XPATH = "*//input[@id='address-ui-widgets-enterAddressStateOrRegion']";
    private static final String ZIP_INPUT_XPATH = "*//input[@id='address-ui-widgets-enterAddressPostalCode']";
    private static final String PHONE_NUMBER_INPUT_XPATH = "*//input[@id='address-ui-widgets-enterAddressPhoneNumber']";
    private static final String ADD_ADDRESS_BUTTON_XPATH = "*//form[@id='address-ui-address-form']//input[@type='submit']";
    private Login login;

    private final SheetManagement sheetManagement;

    public Address(WebDriver driver, Login login) {
        super(driver);
        this.sheetManagement = SheetManagement.getInstance();
        this.login = login;
    }

    public void setShippingAddress() throws IOException {
        driver.get("https://www.amazon.com/a/addresses");
        
        if(driver.getCurrentUrl().contains("signin")){
            this.login.reLogin();
        }
        
        removeUnusedAddress();
        clickAddNewAddress();
        waitUtilElementVisible(By.xpath(COUNTRY_SELECT_XPATH));
        Select countryDropdown = new Select(driver.findElement(By.xpath(COUNTRY_SELECT_XPATH)));
        countryDropdown.selectByValue("US");
        driver.findElement(By.xpath(FULL_NAME_INPUT_XPATH)).sendKeys(getShippingFullname());
        driver.findElement(By.xpath(ADDRESS_LINE1_INPUT_XPATH)).sendKeys(this.sheetManagement.getDataInColumn(ApplicationContext.ADDRESS_1_COLUMN));
        driver.findElement(By.xpath(ADDRESS_LINE2_INPUT_XPATH)).sendKeys(this.sheetManagement.getDataInColumn(ApplicationContext.ADDRESS_2_COLUMN));
        driver.findElement(By.xpath(CITY_INPUT_XPATH)).sendKeys(this.sheetManagement.getDataInColumn(ApplicationContext.CITY_COLUMN));
        driver.findElement(By.xpath(STATE_INPUT_XPATH)).sendKeys(this.sheetManagement.getDataInColumn(ApplicationContext.STATE_COLUMN));
        driver.findElement(By.xpath(ZIP_INPUT_XPATH)).sendKeys(this.sheetManagement.getDataInColumn(ApplicationContext.ZIP_COLUMN));
        driver.findElement(By.xpath(PHONE_NUMBER_INPUT_XPATH)).sendKeys(this.sheetManagement.getDataInColumn(ApplicationContext.PHONE_COLUMN));
        clickAddAddress();
    }

    private void removeUnusedAddress() throws IOException {
        List<WebElement> removeLinks = driver.findElements(By.xpath(DELETE_ADDRESS_XPATH));
        if (removeLinks != null && removeLinks.size() > 0) {
            clickElement(By.xpath(DELETE_ADDRESS_XPATH + "[1]"));
            clickElement(By.xpath(CONFIRM_DELETE_XPATH + "[1]"));
            waitUtilPageLoad();
            removeUnusedAddress();
        }
    }
    
    private void clickAddNewAddress(){
        clickElement(By.xpath(ADD_ADDRESS_XPATH));
        waitUtilPageLoad();
    }

    private void clickAddAddress() throws IOException {
        clickElement(By.xpath(ADD_ADDRESS_BUTTON_XPATH));
    }

    private String getShippingFullname() throws IOException {
        return this.sheetManagement.getDataInColumn(ApplicationContext.SHIPPING_NAME_COLUMN)
                + " "
                + this.sheetManagement.getDataInColumn(ApplicationContext.SHIPPING_LAST_NAME_COLUMN);
    }
}
