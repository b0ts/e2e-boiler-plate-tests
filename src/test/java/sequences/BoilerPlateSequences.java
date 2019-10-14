package scenarios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;


// demonstrated the page object model way of refactoring Appium tests
import appium_pages.SplashPage;
import appium_pages.LoginPage;
import appium_pages.AdminPage;
import appium_pages.ProfileEditPage;


public class BoilerPlateSequences {
    private AppiumDriver appiumDriver;
    private SplashPage splash;
    private LoginPage login;
    private AdminPage admin;
    private ProfileEditPage profileEdit;

    public BoilerPlateSequences(AppiumDriver appiumDriver) {
       this.appiumDriver = appiumDriver;
       this.splash = new SplashPage(appiumDriver);
       this.login = new LoginPage(appiumDriver);
       this.admin = new AdminPage(appiumDriver);
       this.profileEdit = new ProfileEditPage(appiumDriver);
    }

    // overloading for default value
    public void login(String username, String password) {
        login(username, password, false);
    }
    public void login(String username, String password, boolean expectError)
    {
        // Activate Brightwheel App from OS - only needed for helloworld hack;
        appiumDriver.findElement(By.xpath("//*[@text='brightwheel']")).click();

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
        new TouchAction(appiumDriver).press(830, 1603).waitAction(4596).moveTo(842, 372).release().perform();

        this.profileEdit.clickLogoutButton();

        this.splash.verifyNavigation();
        appiumDriver.closeApp();
    }

}
