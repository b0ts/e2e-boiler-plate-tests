package appium_pages;

import appium_hierarchy.PageBase;
import io.appium.java_client.AppiumDriver;


public class SplashPage extends PageBase {
	private String signinSelector;
	private String backSelector;

    public SplashPage(AppiumDriver appiumDriver) {
        super(appiumDriver, "//*[@id='welcome_title']");
        signinSelector = "//*[@id='signinButton']";  // same for both Android and iOS
        backSelector = (phoneType == "Android") ? "" : "//*[@id='back']";
    }
    public void navigateToLoginPage() { tapWhenTappable(signinSelector); }
    
    public void navigateToOs() {
        verifyNavigation();
		back(backSelector);
    }
}
