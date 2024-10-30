package com.chaseTest.automation.ui.qa.pages;

import com.chaseTest.automation.ui.qa.core.SharedDriver;
import com.chaseTest.automation.ui.qa.serenity.PageObject;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class JPWebPage extends PageObject {

    @FindBy(id = "W0wltc")
    private WebElement rejectCookies;

    @FindBy(id = "APjFqb")
    private WebElement searchTextField;

    @FindBy(id = "jZ2SBf")
    private WebElement firstSearchResult;

    @FindBy(className = "byrV5b")
    private WebElement firstResultLink;

    @FindBy(id = "Alh6id")
    private WebElement resultsDropDown;

    @FindBy(name = "btnK")
    private WebElement googleSearchBtn;

    @FindBy(id = "onetrust-close-btn-container")
    private WebElement JPCookiesCloseBtn;

    @FindBy(className = "primary-navigation-logo")
    private WebElement JPLogo;


    public void open_google() {
        openAt("https://www.google.com");
        waitForPageLoad();
        clickOn(rejectCookies);
    }

    public void enter_and_search_the_text_using_search_field(String searchText) {
        searchTextField.sendKeys(searchText);
        if(isDisplayed(resultsDropDown)) {
            clickOn(firstSearchResult);
        } else {
            clickOn(googleSearchBtn);
        }
    }

    public void click_on_first_result_link() {
        waitABit(5000);
        clickOn(firstResultLink);
        waitABit(5000);
    }

    public void verify_URL() {
        waitForPageLoad();
        String URL = getDriver().getCurrentUrl();
        Assert.assertEquals(URL, "https://www.jpmorgan.com/global");
        Serenity.recordReportData().withTitle("URL").andContents(URL);
        waitABit(5000);
    }

    public void close_JP_Web_Cookies() {
        waitABit(5000);
        if(JPCookiesCloseBtn.isEnabled()) {
            clickOn(JPCookiesCloseBtn);
        }
    }

    public void verify_JP_Web_Page() {
        verify_URL();
//        close_JP_Web_Cookies();
    }

    public boolean verify_JP_logo() {
        return JPLogo.isDisplayed();
    }
}
