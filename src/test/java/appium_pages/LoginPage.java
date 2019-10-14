package appium_pages;

import appium_hierarchy.PageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertTrue;

public class LoginPage extends PageBase {

    public LoginPage(AppiumDriver appiumDriver) {
        super(appiumDriver, "//*[@id='welcome_back']");
    }

    public void fillOutForm(String email, String pass) {
        appiumDriver.findElement(By.xpath("//*[@id='email_or_phone_input']")).sendKeys(email);
        appiumDriver.findElement(By.xpath("//*[@id='password_input']")).sendKeys(pass);
    }

    public void clickSignInButton() {
        appiumDriver.findElement(By.xpath("//*[@text='Sign in']")).click();
    }

    public void verifyAndDismissErrorDialog() {
        new WebDriverWait(appiumDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Error']")));
        String actual = appiumDriver.findElement(By.xpath("//*[@id='message']")).getText();
        // the first tests actually found a bug in the code as the error messages should read the same
        assertTrue("Error Message contains ", actual.equals("Invalid phone number or password")
                || actual.equals("Invalid email or password"));
        appiumDriver.findElement(By.xpath("//*[@text='OK']")).click();
    }

    public void navigateBackToSplashScreen() {
        new WebDriverWait(appiumDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@contentDescription='Navigate up']")));
        appiumDriver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();
    }


}
