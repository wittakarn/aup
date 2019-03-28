/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.page;

import static com.thaisoftplus.aup.util.PageHelper.LONG_WAIT_TIMEOUT;
import java.io.Serializable;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Administrator
 */
public class BasePage implements Serializable {

    protected static final int TIMEOUT = 5;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUtilPageLoad() {
        Wait<WebDriver> wait = new WebDriverWait(driver, LONG_WAIT_TIMEOUT);
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    protected WebElement getFirstElement(WebElement element, By by) {
        List<WebElement> elements = element.findElements(by);
        if (elements != null && elements.size() > 0) {
            return elements.get(0);
        }
        return null;
    }

    protected String getDefaultText(WebElement element) {
        return element == null ? "" : element.getText();
    }

    protected WebElement getLastElement(WebElement element, By by) {
        List<WebElement> elements = element.findElements(by);
        if (elements != null && elements.size() > 0) {
            return elements.get(elements.size() - 1);
        }
        return null;
    }

    protected boolean isElementExist(By by) {
        List<WebElement> elemsnts = driver.findElements(by);
        return elemsnts != null && elemsnts.size() > 0;
    }

    protected void waitUtilElementExist(By by) {
        new WebDriverWait(driver, TIMEOUT) {
        }.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return isElementExist(by);
            }
        });
    }

    protected boolean waitUtilElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected boolean waitUtilElementNotVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected boolean waitUtilElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected boolean waitUtilUrlChanged(String originalUrl) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return !originalUrl.equals(d.getCurrentUrl());
                }
            });
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected void waitForNumberOfWindowsToEqual(final int numberOfWindows) {
        new WebDriverWait(driver, TIMEOUT) {
        }.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (driver.getWindowHandles().size() == numberOfWindows);
            }
        });
    }

    protected void clickElement(By by) {
        waitUtilElementClickable(by);
        driver.findElement(by).click();
    }

    protected void simulatePerformClick(By by) {
        Actions builder = new Actions(driver);
        WebElement webElement = driver.findElement(by);
        builder.moveToElement(webElement).click(webElement);
        builder.perform();
    }

    protected void simulateUserType(By by, String typeText) {
        char[] characters = typeText.toCharArray();
        waitUtilElementVisible(by);
        for (char character : characters) {
            driver.findElement(by).sendKeys(String.valueOf(character));
        }
    }

    protected void simulateJavaScriptClick(String querySelector) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.querySelector('" + querySelector + "').click()");
    }
}
