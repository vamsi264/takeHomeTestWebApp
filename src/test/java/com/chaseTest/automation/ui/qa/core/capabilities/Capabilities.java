package com.chaseTest.automation.ui.qa.core.capabilities;

import org.openqa.selenium.MutableCapabilities;

public class Capabilities {

    private DriverCapabilities driverCapabilities;

    public MutableCapabilities getCapabilities() { return driverCapabilities.getCapabilities(); }

    public void setCapabilities(DriverCapabilities driverCapabilities) {
        this.driverCapabilities = driverCapabilities;
    }
}
