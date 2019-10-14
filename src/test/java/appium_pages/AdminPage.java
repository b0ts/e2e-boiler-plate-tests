package appium_pages;

import appium_hierarchy.PageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertTrue;

public class AdminPage extends PageBase {

    public AdminPage(AppiumDriver appiumDriver) {
        super(appiumDriver, "//*[@text and ./parent::*[@id='toolbar']]", "Administrator Home");
    }

    public void navigateToProfileEdit() {
        // Click the Nav Drawer Popout

        new WebDriverWait(appiumDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text and ./parent::*[@id='toolbar']]")));
        String toolBarTitle = appiumDriver.findElement(By.xpath("//*[@text and ./parent::*[@id='toolbar']]")).getText();
        assertTrue("Toolbar Title contains Administrator Home", toolBarTitle.contains("Administrator Home"));
        appiumDriver.findElement(By.xpath("//*[@contentDescription='Open navigation drawer']")).click();

        // Use Nav Drawer to Nav to Edit Profile Page;
        new WebDriverWait(appiumDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='edit']")));
        appiumDriver.findElement(By.xpath("//*[@id='edit']")).click();
    }

}
