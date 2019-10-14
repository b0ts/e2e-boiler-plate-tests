package scenarios;

import lib.SharedUtil;
import lib.SeleniumDriverUtil;
import lib.AppiumDriverUtil;
import lib.ShellCommand;

import java.net.MalformedURLException;


// Space to hold instantiation and cleanup from @beforeClass, @afterClass, etc.
public class TestBase {
    public SharedUtil util;
    public ShellCommand cmd;
    public SeleniumDriverUtil selenium;
    public AppiumDriverUtil appium;

    protected void createUtilsAndDrivers() throws MalformedURLException {
        util = new SharedUtil();
        cmd = new ShellCommand();
        selenium = new SeleniumDriverUtil();
        selenium.setup();
        appium = new AppiumDriverUtil();
        appium.setup();
    }

    protected void cleanupUtilsAndDrivers() {

        if (appium.driver != null) {
            appium.driver.quit();
            appium.driver = null;
        }
        if (selenium.driver != null) {
            selenium.driver.close();
            selenium.driver.quit();
            selenium.driver = null;
        }
        selenium = null;
        appium = null;
        cmd = null;
        util = null;
    }
}
