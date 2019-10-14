package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertTrue;

public class SplashPage extends BasePage {

    public SplashPage(WebDriver driver) {
        super(driver);
    }

    public SplashPage verifyNavigation() {

        // example of syncronization
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='brightwheel']")));
        String actual = driver.findElement(By.xpath("//*[@id='brightwheel']")).getText();

        // example of interrogation
        assertTrue("SplashPage Title contains brightwheel", actual.equals("brightwheel"));
        return new SplashPage(driver);
    }

    public SplashPage navigateToLoginPage() {
        // example of click manipulation
        // Click Sign In Button;
        driver.findElement(By.xpath("//*[@id='signinButton']")).click();
        return new SplashPage(driver);
    }

    public SplashPage navigateToOs() {
        verifyNavigation();
        driver.findElement(By.xpath("//*[@id='back']")).click();
        return new SplashPage(driver);
    }
}
