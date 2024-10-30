package com.chaseTest.automation.ui.qa.core;

import com.chaseTest.automation.ui.qa.core.capabilities.Capabilities;
import com.chaseTest.automation.ui.qa.core.capabilities.DriverCapabilities;
import com.chaseTest.automation.ui.qa.core.capabilities.FirefoxCapabilities;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.thucydides.core.webdriver.DriverSource;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class SharedDriver implements DriverSource {

    private MutableCapabilities capabilities;
    private DriverCapabilities driverCapabilities;
    private Capabilities capability;
    protected static WebDriver driver;
    public static Scenario scenario;
    protected static DriverServiceManager driverService;
    public static final Properties properties = new Properties();

    private static final String platform = properties.getPlatformName();
    private final String browserName = properties.getBrowserName();


    @Before
    public void setup(Scenario scenario) {
        SharedDriver.scenario = scenario;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driverService.stopDriverService();
        }
    }

    @Override
    public WebDriver newDriver() {
        driverService = new DriverServiceManager();
        BrowserManager browserManager = new BrowserManager(browserName, properties.getDriverVersion());
        browserManager.setupBrowser();
        driverService.setDriverService(browserName);
        capabilities = new MutableCapabilities();
        setCapabilities(capabilities);
        capabilities = capability.getCapabilities();
        driverService.startDriverService();
        driver = new RemoteWebDriver(driverService.getDriverServiceURL(), capabilities);
        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(new Dimension(1706, 802));
        return driver;
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }

    public void setBrowserCapabilities(@NotNull Capabilities capability) {
        if ("firefox".equalsIgnoreCase(browserName)) {
            FirefoxCapabilities firefoxCapabilities = new FirefoxCapabilities();
            driverCapabilities = firefoxCapabilities;
            firefoxCapabilities.setCapabilities(capabilities, properties.enableW3C(), properties.acceptInsecureCerts(), properties.setHeadless());
        } else {
            throw new IllegalStateException("Unexpected value: " + browserName);
        }
        capability.setCapabilities(driverCapabilities);
    }


    public void setCapabilities(@NotNull MutableCapabilities capabilities) {
        capability = new Capabilities();
        capabilities.setCapability(PLATFORM_NAME, platform);
        capabilities.setCapability(BROWSER_NAME, browserName);
        setBrowserCapabilities(capability);
    }

}
