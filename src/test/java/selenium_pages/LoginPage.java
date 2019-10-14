package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertTrue;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage verifyNavigation() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='welcome_back']")));
        return new LoginPage(driver);
    }

    public void fillOutForm(String email, String pass) {
        driver.findElement(By.xpath("//*[@id='email_or_phone_input']")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id='password_input']")).sendKeys(pass);
    }

    public void clickSignInButton() {
        driver.findElement(By.xpath("//*[@text='Sign in']")).click();
    }

    public void verifyAndDismissErrorDialog() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Error']")));
        String actual = driver.findElement(By.xpath("//*[@id='message']")).getText();
        // the first tests actually found a bug in the code as the error messages should read the same
        assertTrue("Error Message contains ", actual.equals("Invalid phone number or password")
                || actual.equals("Invalid email or password"));
        driver.findElement(By.xpath("//*[@text='OK']")).click();
    }

    public void navigateBackToSplashScreen() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@contentDescription='Navigate up']")));
        driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();
    }


}
