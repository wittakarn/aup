/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.page.amazon;

import com.thaisoftplus.aup.context.ApplicationContext;
import com.thaisoftplus.aup.exception.QuantityNotEnoughException;
import com.thaisoftplus.aup.googlel.sheet.SheetManagement;
import com.thaisoftplus.aup.page.BasePage;
import com.thaisoftplus.aup.util.PageHelper;
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
public class ShoppingCart extends BasePage implements Serializable {

    private static final String DROPDOWN_QUANTITY_XPATH = "*//select[@name='quantity']";
    private static final String DROPDOWN_QUANTITY_SELECTED_XPATH = DROPDOWN_QUANTITY_XPATH + "//option[@selected]";
    private static final String QUANTITY_SUMMAARY_XPATH = "//span[@id='sc-subtotal-label-activecart']";
    private static final String PROCESS_TO_CHECKOUT_XPATH = "*//input[@name='proceedToCheckout']";
    private static final String DELETE_PRODUCT_XPATH = "*//input[@type='submit' and @value='Delete']";

    private final SheetManagement sheetManagement;

    public ShoppingCart(WebDriver driver) {
        super(driver);
        sheetManagement = SheetManagement.getInstance();
    }

    public void PerformCheckout() throws Exception {
        openCart();
        String quantity = sheetManagement.getDataInColumn(ApplicationContext.TO_BUY_COLUMN);
        setQuantity(quantity);
        openCart();
        if (!isCorrectQuantity(quantity)) {
            throw new QuantityNotEnoughException("Customer order " + quantity + " but the seller did not have enough product");
        }
        clickCheckout();
    }

    public void clearAllCart() throws IOException {
        openCart();
        List<WebElement> deleteElements = driver.findElements(By.xpath(DELETE_PRODUCT_XPATH));
        if (deleteElements != null && deleteElements.size() > 0) {
            waitUtilElementClickable(By.xpath(DELETE_PRODUCT_XPATH));
            driver.findElement(By.xpath(DELETE_PRODUCT_XPATH)).click();
            driver.navigate().refresh();
            clearAllCart();
        }
    }

    private void openCart() {
        driver.get("https://www.amazon.com/gp/cart/view.html");
        waitUtilPageLoad();
    }

    private boolean isCorrectQuantity(String quantity) throws IOException {
        waitUtilElementVisible(By.xpath(DROPDOWN_QUANTITY_XPATH));
        Select quantityDropdown = new Select(driver.findElement(By.xpath(DROPDOWN_QUANTITY_XPATH)));
        String selectedQuantity = quantityDropdown.getFirstSelectedOption().getAttribute("value");
        return selectedQuantity.equals(quantity);
    }

    private void setQuantity(String quantity) throws IOException {
        waitUtilElementVisible(By.xpath(DROPDOWN_QUANTITY_XPATH));

        String defaultQuantity = driver.findElement(By.xpath(DROPDOWN_QUANTITY_SELECTED_XPATH)).getAttribute("value");
        String defaultQuantitySummary = driver.findElement(By.xpath(QUANTITY_SUMMAARY_XPATH)).getText();
        if (!quantity.equals(defaultQuantity)) {
            Select quantityDropdown = new Select(driver.findElement(By.xpath(DROPDOWN_QUANTITY_XPATH)));
            quantityDropdown.selectByValue(quantity);
            PageHelper.waitForTextChanged(driver, By.xpath(QUANTITY_SUMMAARY_XPATH), defaultQuantitySummary);
        }
    }

    private void clickCheckout() throws IOException {
        waitUtilElementClickable(By.xpath(PROCESS_TO_CHECKOUT_XPATH));
        driver.findElement(By.xpath(PROCESS_TO_CHECKOUT_XPATH)).click();
    }
}
