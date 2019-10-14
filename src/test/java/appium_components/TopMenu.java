package appium_components;
// Note:  The appium_components folder houses objects like menues that span multiple appium_pages.

import appium_hierarchy.PageBase;
import io.appium.java_client.AppiumDriver;

public class TopMenu extends PageBase {
	// Note:  These need to be converted to more descriptive names
    private String firstMenuButtonId, secondMenuButtonId, thirdMenuButtonId, 
    fourthMenuButtonId, fifthMenuButtonId, sixthMenuButtonId, seventhMenuButtonId,
    helpMenuButtonId, appVersionId;

    public TopMenu(AppiumDriver appiumDriver, String phoneType) {
    	// Note:  These need to be converted to real selectors and if they differ 
    	// between Android and iOS then conditionally use the correct one.
        super(appiumDriver, "//*[@id='UniqueMenuSelector']");
        firstMenuButtonId = "//*[@id='firstMenuSelector']";
        secondMenuButtonId = "//*[@id='secondMenuSelector']";
        thirdMenuButtonId = "//*[@id='thirdMenuSelector']";
        fourthMenuButtonId = "//*[@id='forthMenuSelector']";
        fifthMenuButtonId = "//*[@id='fifthMenuSelector']";
        sixthMenuButtonId = "//*[@id='sixthMenuSelector']";
        seventhMenuButtonId = "//*[@id='seventhMenuSelector']"; 
        helpMenuButtonId = "//*[@id='helpMenuSelector']";
        appVersionId = "//*[@id='appVersionSelector']";
    }

    public void tapFirstMenuButton() { this.tapWhenTappable(firstMenuButtonId); }

    public void tapSecondMenuButton() { this.tapWhenTappable(secondMenuButtonId); }

    public void tapThirdMenuButton() { this.tapWhenTappable(thirdMenuButtonId); }

    public void tapFourthMenuButton() { this.tapWhenTappable(fourthMenuButtonId); }
    
    public void tapFifthMenuButton() { this.tapWhenTappable(fifthMenuButtonId); }

    public void tapSixthMenuButton() { this.tapWhenTappable(sixthMenuButtonId); }

    public void tapSeventhMenuButton() { this.tapWhenTappable(seventhMenuButtonId); }

    public void tapHelpButton() { this.tapWhenTappable(helpMenuButtonId); }

    public void logAppVersion() { this.logTextField(appVersionId, "App Version: ");}

}
