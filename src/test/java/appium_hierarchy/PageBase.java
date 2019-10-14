package appium_hierarchy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class PageBase extends ComponentBase {
    private String targetElementXPath;
    private String targetElementText = "";
    private int maxTimeout = 20;

    public PageBase(AppiumDriver driver, String targetElementXPath) {
        super(driver);
        this.targetElementXPath = targetElementXPath;
    }

    public PageBase(AppiumDriver driver, String targetElementXPath, String targetElementText) {
        super(driver);
        this.targetElementXPath = targetElementXPath;
        this.targetElementText = targetElementText;
    }


	// This is abstracted in the Appium base - so that all appium_pages can verify navigation easily
    public void verifyNavigation(int timeOutInSeconds) {
        new WebDriverWait(appiumDriver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.targetElementXPath)));
        if (this.targetElementText.length()>0) {
            String actualText = appiumDriver.findElement(By.xpath(this.targetElementXPath)).getText();


            String goober = "***" + actualText + "***";
            System.out.println(goober);

            //this can go forever - so needs timer
            while ((actualText == "") || (actualText != "Welcome")) {
                actualText = appiumDriver.findElement(By.xpath(this.targetElementXPath)).getText();
                goober = "***" + actualText + "***";
                System.out.println(goober);
            }
            assertEquals(actualText, this.targetElementText, "Verifying Navigation");

            }
    }
    // overloaded to provide the maximum timeout for page activation - can be adjusted per app
    public void verifyNavigation() {
        this.verifyNavigation(this.maxTimeout);
    }
    
    public void back(String backSelector) {
    	if (backSelector == "") { // On Android we don't need the selector for the back arrow
        	((AndroidDriver)appiumDriver).sendKeyEvent(AndroidKeyCode.BACK);
        } else { // on iOS we do
        	this.tapWhenTappable(backSelector);
        }
    }

}
