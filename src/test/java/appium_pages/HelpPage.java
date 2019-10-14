package appium_pages;

import appium_hierarchy.PageBase;
import io.appium.java_client.AppiumDriver;


public class HelpPage extends PageBase {
    private String backButtonId, lightColorThemeButtonId, darkColorThemeButtonId,
            fahrenheitButtonId, celsiusButtonId;

    public HelpPage(AppiumDriver appiumDriver) {
        super(appiumDriver, "//*[@text='Help & Support']");
        this.backButtonId = "//*[@contentDescription='Navigate up']";
    }

    public void tapBackButton() { this.tapWhenTappable(this.backButtonId); }
}
