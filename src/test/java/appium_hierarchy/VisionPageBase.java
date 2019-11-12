package appium_hierarchy;

// Deriving a page class from VisionPageBase adds computer vision functionality for apps such as games that
// only display images on the screen, so we need to perform image processing in order to find elements and
// their coordinates to know where to click
// Note: Appium Image processing is derived from https://github.com/Simon-Kaz/AppiumFindByImage

import io.appium.java_client.AppiumDriver;

public class VisionPageBase extends PageBase {


    public VisionPageBase(AppiumDriver driver, String targetElementXPath) {
        super(driver, targetElementXPath);
    }

    public VisionPageBase(AppiumDriver driver, String targetElementXPath, String targetElementText) {
        super(driver, targetElementXPath, targetElementText);
    }

    // internal class to add computer vision functionality
    public class CV {

    }

}
