package scenarios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;

import lib.OCR;

public class OCRNekoAtsumeTestSuite {
    private AppiumDriver driver;
    private WebDriverWait wait;
    private OCR OCR;
    private File imgDir;

    @Before
    public void setUp() throws Exception {

        //Appium setup for the app
        //needs to be installed on target device before the test
        // Note:  The Niko Cat's app needs to be installed on the device and a cat feed bowl added
        // prior to starting test
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appPackage", "jp.co.hit_point.nekoatsume");
        capabilities.setCapability("appActivity", "jp.co.hit_point.nekoatsume.GActivity");

        // capabilities.setCapability("deviceName", "Android Emulator");
        // capabilities.setCapability("platformVersion", "5.0.1");

        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("deviceName", "Pixel 2");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        wait = new WebDriverWait(driver, 10);

        //Sikuli settings
        OCR = new OCR(driver);

        //location of screenshots
        File classpathRoot = new File(System.getProperty("user.dir"));
        imgDir = new File(classpathRoot, "imgs");


        //switch to native app + portrait mode
        driver.context("NATIVE_APP");
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void refillTest() {
        String foodBowlImgLoc = imgDir + "/foodBowl.png";
        String yesBowlImgLoc = imgDir + "/yesBowl.png";

        OCR.waitUntilImageExists(foodBowlImgLoc, 30);
        OCR.clickByImage(foodBowlImgLoc);
        OCR.waitUntilImageExists(yesBowlImgLoc, 30);
        OCR.clickByImage(yesBowlImgLoc);
    }

}
