package com.chaseTest.automation.ui.qa.core.capabilities;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT;
import static org.openqa.selenium.firefox.FirefoxDriverLogLevel.ERROR;
import static org.openqa.selenium.firefox.FirefoxOptions.FIREFOX_OPTIONS;

public class FirefoxCapabilities implements DriverCapabilities {

    private MutableCapabilities capabilities;
    private boolean w3c, acceptInsecureCerts, headless;
    private String pageLoadStrategy;

    @Override
    public MutableCapabilities getCapabilities() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setUnhandledPromptBehaviour(ACCEPT);
        firefoxOptions.setCapability("w3c", w3c);
        firefoxOptions.setLogLevel(ERROR);
        firefoxOptions.setHeadless(headless);
        firefoxOptions.setAcceptInsecureCerts(acceptInsecureCerts);

        capabilities.setCapability(FIREFOX_OPTIONS, firefoxOptions);
        return capabilities;
    }

    public void setCapabilities(MutableCapabilities capabilities, boolean w3c, boolean acceptInsecureCerts, boolean headless) {
        this.capabilities = capabilities;
        this.w3c = w3c;
        this.acceptInsecureCerts = acceptInsecureCerts;
        this.headless = headless;
        this.pageLoadStrategy = pageLoadStrategy;
    }
}
