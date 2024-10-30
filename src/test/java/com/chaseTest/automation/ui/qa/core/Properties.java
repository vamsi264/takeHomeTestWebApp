package com.chaseTest.automation.ui.qa.core;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.Boolean.parseBoolean;
import static java.lang.String.format;

public class Properties {

    private static final Logger LOGGER = LoggerFactory.getLogger(Properties.class);
    public String getBrowserName() {
        return getProperty("selenium.browser.name");
    }

    public String getDriverVersion() {
        return getProperty(format("selenium.driver.%s", getBrowserName()));
    }

    public String getPlatformName() {
        return getProperty("platform.name");
    }

    public String getEnvironmentUrl() {
        return getProperty("environments");
    }
    public boolean enableW3C() {
        return parseBoolean(getProperty("selenium.browser.capabilities.w3c"));
    }

    public boolean acceptInsecureCerts() {
        return parseBoolean(getProperty("selenium.browser.capabilities.acceptInsecureCerts"));
    }

    public boolean setHeadless() {
        return parseBoolean(getProperty("selenium.browser.capabilities.headless"));
    }

    private String getProperty(String key) {
        if (StringUtils.isEmpty(key)) {
            String message = format("%s cannot be null, empty or blank.", key);
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }
        EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();
        System.out.printf("key: %s", key);
        return EnvironmentSpecificConfiguration.from(env).getProperty(key);
    }
}
