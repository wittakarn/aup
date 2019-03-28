/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.util;

import java.io.Serializable;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author witta
 */
public class PageHelper implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(PageHelper.class);
    public static final int WAIT_TIMEOUT = 2;
    public static final int LONG_WAIT_TIMEOUT = 10;

    public static void waitUtilPageLoad(WebDriver driver) {
        Wait<WebDriver> wait = new WebDriverWait(driver, LONG_WAIT_TIMEOUT);
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    public static void waitElementsClickableAndClick(WebDriver driver, By locator, int index) {
        WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT_TIMEOUT);
        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                List<WebElement> elements = driver.findElements(locator);
                try {
                    if (elements != null && elements.size() > 0) {
                        WebElement element = elements.get(index);
                        if (element != null && element.isDisplayed() && element.isEnabled()) {
                            element.click();
                            return element;
                        }
                        return null;
                    }
                    return null;
                } catch (StaleElementReferenceException e) {
                    logger.error("waitElementsClickableAndClick", locator.toString());
                    return null;
                }
            }
        });
    }

    public static void waitElementClickableAndClick(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public static void waitForElementInVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT_TIMEOUT);

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    List<WebElement> elements = driver.findElements(locator);
                    for (WebElement element : elements) {
                        if (element.isDisplayed()) {
                            return false;
                        }
                    }
                    return true;
                } catch (NoSuchElementException e) {
                    // Returns true because the element is not present in DOM. The
                    // try block checks if the element is present but is invisible.
                    logger.error("waitForElementInVisible", locator.toString());
                    return true;
                } catch (StaleElementReferenceException e) {
                    // Returns true because stale element reference implies that element
                    // is no longer visible.
                    logger.error("waitForElementInVisible", locator.toString());
                    return true;
                }
            }
        });
    }

    public static int getElementsVisibleCount(WebDriver driver, By locator) {
        int visibleCount = 0;
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                visibleCount++;
            }
        }
        return visibleCount;
    }

    public static void waitOneOfElementsVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT_TIMEOUT);

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    List<WebElement> elements = driver.findElements(locator);
                    for (WebElement element : elements) {
                        return element.isDisplayed();
                    }
                    return false;
                } catch (NoSuchElementException e) {
                    // Returns true because the element is not present in DOM. The
                    // try block checks if the element is present but is invisible.
                    logger.error("waitOneOfElementsVisible", locator.toString());
                    return false;
                } catch (StaleElementReferenceException e) {
                    // Returns true because stale element reference implies that element
                    // is no longer visible.
                    logger.error("waitOneOfElementsVisible", locator.toString());
                    return false;
                }
            }
        });
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception ex) {
            //logger.error("", ex);
            return null;
        }
    }

    public static void waitForTextChanged(WebDriver driver, By locator, String oldValue) {
        WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT_TIMEOUT);
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    try {
                        if (driver.findElement(locator).isDisplayed() && !oldValue.equals(driver.findElement(locator).getText())) {
                            return true;
                        } else {
                            return false;
                        }
                    } catch (NoSuchElementException e) {
                        // Returns true because the element is not present in DOM. The
                        // try block checks if the element is present but is invisible.
                        logger.error("waitForTextChanged: NoSuchElementException", e);
                        return true;
                    } catch (StaleElementReferenceException e) {
                        // Returns true because stale element reference implies that element
                        // is no longer visible.
//                        logger.error("waitForTextChanged: StaleElementReferenceException", e);
                        return true;
                    }
                }
            });
        } catch (Exception ex) {
            logger.error("Wait until text change from ".concat(oldValue), ex);
        }
    }
}
