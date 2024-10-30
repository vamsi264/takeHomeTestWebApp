package com.chaseTest.automation.ui.qa.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import net.serenitybdd.core.Serenity;

import java.net.URL;

import static io.github.bonigarcia.wdm.config.DriverManagerType.FIREFOX;
import static java.lang.String.format;

public class BrowserManager {

    private String browser,driverVersion;

    private final Config config;

    DriverManagerType driverManagerType;
    URL driverRepositoryUrl;
    /**
     * Initialize browser driver
     *
     * @param browser parameter, i.e. chrome etc.
     */
    public BrowserManager(String browser, String driverVersion) {
        this.browser = browser;
        this.driverVersion = driverVersion;
        config = WebDriverManager.getInstance(browser).config();
        config.reset();
    }

    public void setupBrowser() {
        if ("firefox".equalsIgnoreCase(browser)) {
            driverManagerType = FIREFOX;
            driverRepositoryUrl = config.getFirefoxDriverUrl();
        } else {
            String message = format("Failed to create driver service for %s.", browser);
            throw new IllegalArgumentException(message);
        }
        WebDriverManager wdm = WebDriverManager.getInstance(driverManagerType);
        wdm.driverRepositoryUrl(driverRepositoryUrl)
                .driverVersion(driverVersion)
                .clearDriverCache()
                .avoidOutputTree()
                .setup();
        Serenity.setSessionVariable("Downloaded Driver Path").to(wdm.getDownloadedDriverPath());
    }
}
