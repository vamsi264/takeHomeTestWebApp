package com.chaseTest.automation.ui.qa.serenity;

import net.thucydides.core.webdriver.exceptions.ElementNotFoundAfterTimeoutError;
import net.thucydides.core.webdriver.exceptions.ElementNotVisibleAfterTimeoutError;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.NoSuchElementException;

import static java.lang.String.format;

public abstract class PageObject extends net.thucydides.core.pages.PageObject {

    private String text;
    private static final long TIMEOUT = 15L;

    private static final Logger LOGGER = LoggerFactory.getLogger(PageObject.class);


    public boolean isDisplayed(WebElement element, long timeout) {
        try {
            return waitFor(element).withTimeoutOf(Duration.ofSeconds(timeout)).isDisplayed();
        } catch (NotFoundException | NullPointerException | StaleElementReferenceException | TimeoutException | NoSuchElementException | ElementNotVisibleException | ElementNotVisibleAfterTimeoutError | ElementNotFoundAfterTimeoutError exception) {
            LOGGER.warn(format("Element (%s) was not displayed after %s, due to %s", element, timeout, exception.getCause()));
            return false;
        }
    }

    public boolean isDisplayed(WebElement element) {
        return isDisplayed(element, TIMEOUT);
    }

    public WebElement waitUntilVisible(WebElement element, long timeout) {
        try {
            return waitFor(element).withTimeoutOf(Duration.ofSeconds(timeout));
        } catch (NotFoundException | NullPointerException | StaleElementReferenceException | TimeoutException | NoSuchElementException | ElementNotVisibleException | ElementNotVisibleAfterTimeoutError | ElementNotFoundAfterTimeoutError exception) {
            text = format("Element (%s) was not visible after %s, due to %s", element, timeout, exception.getCause());
            LOGGER.error(text);
            throw new ElementNotFoundAfterTimeoutError(text, exception);
        }
    }

    public WebElement waitUntilVisible(WebElement element) {
        return waitUntilVisible(element, TIMEOUT);
    }

    public void waitForPageLoad() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(50)).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

}
