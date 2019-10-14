package appium_pages;

import appium_hierarchy.PageBase;
import io.appium.java_client.AppiumDriver;


public class GrantAccessPage extends PageBase {
    private String continueButtonId, allowAccessButtonId;

    public GrantAccessPage(AppiumDriver appiumDriver) {
        super(appiumDriver, "//*[@text='Grant Access']");
        this.continueButtonId = "//*[@id='request_location_continue']";
        this.allowAccessButtonId = "//*[@id='permission_allow_button']";
    }

    public void tapContinueButton() { this.tapWhenTappable(this.continueButtonId); }
    public void tapAllowAccessButton() { this.tapWhenTappable(this.allowAccessButtonId); }
}
