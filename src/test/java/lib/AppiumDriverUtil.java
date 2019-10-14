package lib;
// Note: Set of basic utilities that contains and adds to Appium Driver functionality

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverUtil {

    public AppiumDriver driver;
    private SharedUtil util;
    private String phoneType, logging, appSelector;

    public AppiumDriver setup() throws MalformedURLException {
        if (util == null) {
            util = new SharedUtil();
        }
        if (driver == null) {
            String phoneType = System.getenv("PHONE_TYPE");
            if (phoneType == null) {
                phoneType = "NONE";
                util.log("Environmental Variable PHONE_TYPE Not Set - using " + phoneType);
            }
            if (!phoneType.equals("NONE")) {
                util.log("Test run targeting: " + phoneType + " phone");
            }

            switch (phoneType) {
                case "ANDROID":
                    driver = prepareAndroidForAppium();
                    break;
                case "IOS":
                    driver = prepareIOSForAppium();
                    break;
                default:
                    driver = null;
            }
        }
        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private AppiumDriver prepareAndroidForAppium() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        // Note:  This changes specifying the Android device that is being used by the Appium Server or Studio
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "Pixel 2");


        // app to upload to device
        // Note:  For a real test you would substitute your APK and name it here
        desiredCapabilities.setCapability("app", "/Users/Shared/code/e2e-boiler-plate-tests/apps/helloworld.apk"); //ok

        return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

    }

    private AppiumDriver prepareIOSForAppium() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        // Note: This changes specify the iOS device that is being used by the Appium Server or Studio
        desiredCapabilities.setCapability("browserName", "");
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("platformVersion", "12.3.1");
        desiredCapabilities.setCapability("deviceName", "iPhone XR");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("fullReset", "true");

        // app to upload to your device
        // Nore:  For a real test suite, you would substitute your IPA and name it here
        desiredCapabilities.setCapability("app", "/Users/Shared/code/e2e-boiler-plate-tests/apps/helloworld.ipa");

        return new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

    }

	// Note: For debugging, sometimes we want to take a look at the page source
    public void displayPageSource() {
        if (driver != null) {
            String pageSource = driver.getPageSource();
            util.log("*** Page Source Begin:");
            util.log(pageSource);
            util.log("*** Page Source End");
        }
    }

    // Launches and relaunches the app defined in the config and uploaded by Appium
    public void launchApp() {
        if (driver != null) {
            driver.launchApp();
        }
    }

	// Sometimes we need to close the app to get out to the OS
    public void closeApp() {
        if (driver != null) {
            driver.closeApp();
        }
    }

    // When we are in the OS, we can open any app as long as it is in the system and we have a selector for it
    public void openAppFromOS(String newAppSelector) {
        if (driver != null) {
            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(newAppSelector)));
            driver.findElement(By.xpath(newAppSelector)).click();
        }
    }

	// Sometimes we need to swipe up to get the app into view in the OS prior to clicking
    public void swipeUp() {
        if (driver != null) {
            new TouchAction(driver).press(951, 1763).waitAction(5245).moveTo(909, 169).release().perform();
        }
    }

	// Trick is to open a hello world, close it and then reopen app under test
    public void reopenAppFromOS() {
        if (driver != null) {
            driver.swipe(651, 1857, 672, 421, 3256);
            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.appSelector)));
            driver.findElement(By.xpath(this.appSelector)).click();
        }
    }

	// for some tests, we want to close and reopen to test that a change stuck
    public void closeAndReopenApp() {
        closeApp();
        reopenAppFromOS();
    }

}
