package appium_hierarchy;
// Note:  The appium_hierarchy folder contains a heirarchy of classes that are use by
// appium_components and appium_pages to do things like extend the Appium language by adding
// groups of commands that will be executed together, for example tapWhenTappable 
// waits for an item to be visable, clickable(tappable) and then taps it.

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.HashMap;

import static org.testng.Assert.assertEquals;

public abstract class ComponentBase {

    public AppiumDriver appiumDriver;
    public String phoneType;

    public ComponentBase(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.phoneType = System.getenv("PHONE_TYPE");
    }

    // forces derived classes to implement that the user is where we expect them to be
    abstract public void verifyNavigation();

	// waits for element to be tappable (clickable) and then taps it
    protected void tapWhenTappable(String selector) {
        new WebDriverWait(appiumDriver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
        new WebDriverWait(appiumDriver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
        appiumDriver.findElement(By.xpath(selector)).click();
    }

    // This is to handle conditionals only if they happen to popup
    protected void tapWhenTappableIfDisplayed(String selector) {
        try {
            new WebDriverWait(appiumDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
            appiumDriver.findElement(By.xpath(selector)).click();
        } catch (Exception e) {
            System.out.println("Skipping forward because the Button with " + selector + " is not displayed");
        }
    }

	// sometimes we want to compair and generate a bool
    protected boolean compairTextToString(String selector, String targetText) {
        new WebDriverWait(appiumDriver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
        String actualText = appiumDriver.findElement(By.xpath(selector)).getText();
        return (actualText == targetText);
    }

	// other times we want to assert and fail test if it isn't what we expect it to be
    protected void assertTextField(String selector, String targetText) {
        new WebDriverWait(appiumDriver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
        String actualText = appiumDriver.findElement(By.xpath(selector)).getText();
        assertEquals(actualText, targetText);
    }

	// overloaded to add a message as to why it failed 
    protected void assertTextField(String selector, String targetText, String message) {
        new WebDriverWait(appiumDriver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
        String actualText = appiumDriver.findElement(By.xpath(selector)).getText();
        assertEquals(actualText, targetText, message);
    }

    // used for unconditional console logging for things like App version number
    protected void logTextField(String selector, String message) {
        new WebDriverWait(appiumDriver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
        String actualText = appiumDriver.findElement(By.xpath(selector)).getText();
        System.out.println(message + actualText);
    }

	// wait for text field to be settable and then manipulate app by setting it.
    protected void setTextField(String selector, String newText) {
        new WebDriverWait(appiumDriver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
        appiumDriver.findElement(By.xpath(selector)).sendKeys(newText);
    }

	// Create a webdriver wait and then wait by locator
    protected void waitForVisibilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    // overloaded to use a selector instead of locator
    protected void waitForVisibilityOf(String selector) {
        new WebDriverWait(appiumDriver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
        new WebDriverWait(appiumDriver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selector)));
    }
    
    // create a webdriver wait and then wait for an element to be clickable
    protected void waitForClickabilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

	// used for check boxes to change appium verbiage to be more understandable
    protected boolean isChecked(String selector) {
        waitForVisibilityOf(selector);
        return appiumDriver.findElement(By.xpath(selector)).isSelected();
    }

	// Navigating around using a sequence of swipe commands
    public void scrollPageUp() {
        JavascriptExecutor js = (JavascriptExecutor) appiumDriver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.50);
        swipeObject.put("startY", 0.95);
        swipeObject.put("endX", 0.50);
        swipeObject.put("endY", 0.01);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }
	
	// Different swipe direction
    public void swipeLeftToRight() {
        JavascriptExecutor js = (JavascriptExecutor) appiumDriver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.01);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.9);
        swipeObject.put("endY", 0.6);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

	// Still another
    public void swipeRightToLeft() {
        JavascriptExecutor js = (JavascriptExecutor) appiumDriver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.5);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }
    
    // Sometimes there is a link embedded inside a text view without an associated 
    // selector.  So, this is a way to click around inside the text view to find it.
    // It is kind of cludge, so I am still looking for a better way to do it.
    // This is modified from:
    // https://discuss.appium.io/t/how-can-i-click-on-text-link-inside-of-textview-android-and-ios-both/11984/18
    public void tapLinkInTextView(String selector, int increment) {

        new WebDriverWait(appiumDriver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
        MobileElement element = (MobileElement) appiumDriver.findElement(By.xpath(selector));
        Point coordinate = element.getLocation(); // location of the element in screen coordinates
        int elementX = coordinate.getX();
        int elementY = coordinate.getY();
        Dimension dms = element.getSize();
        int elementW = dms.getWidth();
        int elementH = dms.getHeight();


        for (int i = 0; i < elementH; i += increment) {
            for (int j = 0; j < elementW; j += increment) {
                int clickX = j + elementX;
                int clickY = i + elementY;
                appiumDriver.tap(1, clickX, clickY, 200);
                try {
                    appiumDriver.findElement(By.xpath(selector));
                } catch (Exception e) {
                    i = elementH;
                    j = elementW;
                }
            }
        }
    }
    
    // overloaded to provide a default increment
    public void tapLinkInTextView(String selector) {
        tapLinkInTextView(selector, 40);
    }

}
