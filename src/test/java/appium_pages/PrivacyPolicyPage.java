package appium_pages;

import appium_hierarchy.PageBase;
import io.appium.java_client.AppiumDriver;

public class PrivacyPolicyPage extends PageBase {

    private String backButtonId;

    public PrivacyPolicyPage(AppiumDriver appiumDriver) {
        // Note: Using text= instead of ID because same id on multiple appium_pages
        super(appiumDriver, "//*[@text='Privacy Policy | Sweet Light Studios']");
        this.backButtonId = "//*[@id='back']";
     }
    public void tapBackButton() { this.tapWhenTappable(this.backButtonId); }
}
