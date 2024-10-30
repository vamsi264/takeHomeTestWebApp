package com.chaseTest.automation.ui.qa.core;

import net.thucydides.core.webdriver.DriverConfigurationError;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.remote.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

import static java.lang.String.format;

public class DriverServiceManager {

    private String message;
    private DriverService driverService;
    private static final Logger LOG = LoggerFactory.getLogger(DriverServiceManager.class);

    /**
     *
     */
    public DriverServiceManager() {
    }

    /**
     * Fetches the URL where the initiated driver server is running.
     *
     * @return driver service URL.
     */
    public URL getDriverServiceURL() {
        try {
            return driverService.getUrl();
        } catch (DriverConfigurationError error) {
            message = format("Unable to get driver service URL, due to %s", error.getCause());
            throw new UnreachableBrowserException(message);
        }
    }

    /**
     * Initiates a default driver service for the specified browser.
     */
    public void setDriverService(String browser) {
        if ("firefox".equalsIgnoreCase(browser)) {
            System.out.printf("webdriver.%s.driver: %s%n", browser,"Downloaded Driver Path");
            System.setProperty(format("webdriver.%s.driver", browser), "Downloaded Driver Path");
            driverService = GeckoDriverService.createDefaultService();
        } else {
            message = format("Failed to create driver service for %s.", browser);
            LOG.error(message);
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Start the initiated driver server.
     */
    public void startDriverService() {
        try {
            driverService.start();
        } catch (IOException error) {
            message = format("Unable to start driver service, due to %s", error.getCause());
            throw new UnreachableBrowserException(message);
        }
    }

    /**
     * Stop the initiated driver server.
     */
    public void stopDriverService() {
        try {
            if (driverService.isRunning() && driverService != null) {
                driverService.stop();
            }
        } catch (DriverConfigurationError error) {
            message = format("Unable to stop driver service, due to %s", error.getCause());
            throw new UnreachableBrowserException(message);
        }
    }
}
