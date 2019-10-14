package appium_pages;

import appium_hierarchy.PageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


public class ProfileEditPage extends PageBase {

    public ProfileEditPage(AppiumDriver appiumDriver) {
        super(appiumDriver, "//*[@text='My profile']");
    }

    public void clickLogoutButton() {
         appiumDriver.findElement(By.xpath("//*[@id='logout_button']")).click();
    }

}
