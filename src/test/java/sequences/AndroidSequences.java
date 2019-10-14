package scenarios;

import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;


// demonstrated the page object model way of refactoring Appium tests
import pages.SplashPage;
import pages.LoginPage;
import pages.AdminPage;
import pages.ProfileEditPage;


public class AndroidSequences {
    private AndroidDriver driver;
    private SplashPage splash;
    private LoginPage login;
    private AdminPage admin;
    private ProfileEditPage profileEdit;

    public AndroidSequences(AndroidDriver driver) {
       this.driver = driver;
       this.splash = new SplashPage(driver);
       this.login = new LoginPage(driver);
       this.admin = new AdminPage(driver);
       this.profileEdit = new ProfileEditPage(driver);
    }

    // overloading for default value
    public void login(String username, String password) {
        login(username, password, false);
    }
    public void login(String username, String password, boolean expectError)
    {
        // Activate Brightwheel App from OS - only needed for helloworld hack;
        driver.findElement(By.xpath("//*[@text='brightwheel']")).click();

        // contains example of synchronization and interrogation
        this.splash.verifyNavigation();
        // contains example of click manipulation
        this.splash.navigateToLoginPage();

        this.login.verifyNavigation();
        // example of text manipulation
        this.login.fillOutForm(username, password);
        this.login.clickSignInButton();

        if (expectError) {
            this.login.verifyAndDismissErrorDialog();
            this.login.navigateBackToSplashScreen();
        } else {
            this.admin.verifyNavigation();
        }
    }

    public void logout()
    {
        this.admin.verifyNavigation();
        this.admin.navigateToProfileEdit();

        this.profileEdit.verifyNavigation();

        // demonstrates swipe manipulation
        new TouchAction(driver).press(830, 1603).waitAction(4596).moveTo(842, 372).release().perform();

        this.profileEdit.clickLogoutButton();

        this.splash.verifyNavigation();
        driver.closeApp();
    }

}
