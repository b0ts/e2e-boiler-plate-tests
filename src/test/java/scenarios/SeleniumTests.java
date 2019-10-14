package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import static org.testng.Assert.*;


import java.net.MalformedURLException;
import java.util.List;

public class SeleniumTests extends TestBase {

    @BeforeClass
    public void setUp() throws MalformedURLException {
        createUtilsAndDrivers();
    }
    @AfterClass
    public void tearDown() {
        cleanupUtilsAndDrivers();
    }
    @BeforeMethod
    public void before() {
    }
    @AfterMethod
    public void after() {
    }

    @Test
    public void siteNameTest() {
        util.log("siteNameTest starting");
        selenium.driver.get("https://www.sweetlightstudios.com");
        util.log("url: " + selenium.driver.getCurrentUrl());
        util.log("html: " + selenium.driver.getPageSource());
        assertTrue(selenium.driver.getTitle().startsWith("South San Francisco"));
        util.log("sitNameTest complete");
    }

    @Test
    public void interrogationTest(){
        util.log("interrogationTest starting");
        selenium.driver.get("https://www.sweetlightstudios.com");

        // positive test to verify that an element is present
        selenium.driver.findElement(By.cssSelector("h1.blurb-headline"));

        // negative test to verify that an element isn't present
        try{
            selenium.driver.findElement(By.cssSelector("h1.goober")); // expect this to fail and jump to catch
            // if we do find it then force test to fail
            fail("Found h1.goober when it is supposed to not be here");
        } catch(NoSuchElementException e){
            System.out.println("Expected NoSuchElementException caught e: " + e);
        }
        util.log("interogationTest completed");
    }

    @Test
    public void manipulationTest(){
        util.log("manipulationTest started");
        selenium.driver.get("https://www.sweetlightstudios.com");

        WebElement lovedOnesImageButton = selenium.driver.findElement(By.cssSelector("a.thumbnail[href=\"/loved-ones/\"]"));
        lovedOnesImageButton.click();

        selenium.driver.findElement(By.cssSelector("article.loved-ones")).isDisplayed(); // loved-ones article

        WebElement homeMenuButton = selenium.driver.findElement(By.cssSelector(".menu-thumbnail"));
        homeMenuButton.click();

        selenium.driver.findElement(By.cssSelector("a.thumbnail[href=\"/loved-ones/\"]")).isDisplayed();
        util.log("manipulationTest completed");
    }

    @Test
    public void synchronizationTest(){
        // Note:  Synchronizing with external controls and sites via fluid waits
        util.log("synchronizationTest started");
        selenium.driver.get("https://www.sweetlightstudios.com/contact/");

        selenium.driver.findElement(By.cssSelector("article.contact")).isDisplayed(); // contact article

        WebElement widget = selenium.driver.findElement(By.name("airvisual_widget"));
        new WebDriverWait(selenium.driver,10).until(ExpectedConditions.elementToBeClickable(widget));
        widget.click();

        new WebDriverWait(selenium.driver,10).until(ExpectedConditions.titleContains("California Air Quality Index"));

        selenium.driver.navigate().back(); // come back to Sweet Light Studios

        new WebDriverWait(selenium.driver,10).until(ExpectedConditions.titleContains("South San Francisco"));

        selenium.driver.findElement(By.cssSelector("article.contact")).isDisplayed(); // verify contact article is displayed
        util.log("synchronizationTest completed");
    }

}
