package lib;
// Note: Set of basic utilities that contains and adds to Selenium Driver functionality
// Note: various Webdrivers for example Chromedriver - need to be in path to start browser

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SeleniumDriverUtil {

    public WebDriver driver;

    private SharedUtil util;
    private String phoneType, logging, appSelector;

    public WebDriver setup() {
        if (util == null) {
            util = new SharedUtil();
        }
        if (driver == null) {
            String browser = System.getenv("BROWSER");
            if (browser == null) {
                browser = "NONE";
                util.log("Environmental Variable BROWSER Not Set - using " + browser);
            }
            if (!browser.equals("NONE")) {
                util.log("Test run targeting " + browser + " browser");
            }

            switch (browser) {
                case "CHROME":
                    driver = new ChromeDriver();
                    break;
                case "FIREFOX":
                    driver = new FirefoxDriver(); // currently broken in Catalina
                    break;
                case "SAFARI":
                    driver = new SafariDriver();
                default:
                    driver = null;
            }
        }
        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }


}
