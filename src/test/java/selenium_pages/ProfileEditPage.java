package pages;

import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertTrue;

public class ProfileEditPage extends BasePage {

    public ProfileEditPage(WebDriver driver) {
        super(driver);
    }

    public ProfileEditPage verifyNavigation() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='My profile']")));
        return new ProfileEditPage(driver);
    }

    public void clickLogoutButton() {
         driver.findElement(By.xpath("//*[@id='logout_button']")).click();
    }

}
