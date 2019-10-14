package scenarios;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

public class BrightWheelTests extends TestBase {
    public scenarios.BoilerPlateSequences sequences;

    @BeforeClass
    public void setUp() throws Exception {
        createUtilsAndDrivers();
        this.sequences = new scenarios.BoilerPlateSequences(appium.driver);
    }

    @AfterClass
    public void tearDown() throws Exception {
        cleanupUtilsAndDrivers();
    }

    @Test
    public void closingHelloWorldDummyAppTest()
    {
        // note: creating a dummy app and then closing it is a hack to allow getting the OS during testing
        appium.closeApp();
    }

    @Test
    public void loginTest()
    {
        // Todo - replace with logger
        System.out.println("Brightwheel login test starting");

        // The sequence class contains series of steps that are used by multiple tests
        // Todo - the username and password from environment vars to allow them to be changed for different environments
        this.sequences.login("rhbourbonnais@yahoo.com", "brightwheelisgreat$1");
        this.sequences.logout();

        System.out.println("Brightwheel login test conpleted");
    }

    @Test
    public void loginNoUsernameTest()
    {
        // Once a sequence is in place it can be called by multiple tests with different params
        System.out.println("Brightwheel login no username test starting");
        this.sequences.login("", "brightwheelisgreat$1", true);
        System.out.println("Brightwheel login no passwword test completed");
    }

    @Test
    public void loginNoPasswordTest()
    {
        System.out.println("Brightwheel login no password test starting");
        this.sequences.login("rhbourbonnais@yahoo.com", "", true);
        System.out.println("Brightwheel login no passwword test completed");
    }

    @Test
    public void loginSpoofPasswordTest()
    {
        System.out.println("Brightwheel login spoof password test starting");
        this.sequences.login("rhbourbonnais@yahoo.com", "spoofPassword$1", true);
        System.out.println("Brightwheel login spoof passwword test completed");
    }

    @Test
    public void gettingStartedTest()
    {
        // The getting started and following tests show how I quickly rough out a more complex test prior to refactoring it
        // into better selectors, page objects, and sequences with much more interrogation

        System.out.println("Brightwheel gettingStarted test starting");
        this.sequences.login("rhbourbonnais@yahoo.com", "brightwheelisgreat$1");

        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Getting Started']")));
        appium.driver.findElement(By.xpath("//*[@text='Getting Started']")).click();
        appium.driver.findElement(By.xpath("//*[@text='Customize Your Experience']")).click();
        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Learning Frameworks']")));
        appium.driver.findElement(By.xpath("//*[@text='Learning Frameworks']")).click();
        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();
        appium.driver.findElement(By.xpath("//*[@text='Check-in Settings']")).click();
        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();
        appium.driver.findElement(By.xpath("//*[@text='Default Activities to Staff-Only']")).click();
        appium.driver.findElement(By.xpath("//*[@text='Parents can edit student info']")).click();
        appium.driver.findElement(By.xpath("//*[@text='Parents can edit student info']")).click();
        new WebDriverWait(appium.driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='parent_permissions_switch']")));
        appium.driver.findElement(By.xpath("//*[@id='parent_permissions_switch']")).click();
        appium.driver.findElement(By.xpath("//*[@text='SAVE']")).click();
        appium.driver.findElement(By.xpath("//*[@text='Customize Your Experience']")).click();
        appium.driver.findElement(By.xpath("//*[@id='parent_permissions_switch']")).click();
        appium.driver.findElement(By.xpath("//*[@text='SAVE']")).click();

        appium.driver.findElement(By.xpath("//*[@text='Add Rooms & Students']")).click();
        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();

        // Getting students failing intermittently when adding a nap unless we look at students in room first?
        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();
        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Rooms']")));
        appium.driver.findElement(By.xpath("//*[@text='Rooms']")).click();
        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Alex Demo']")));
        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();

        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Getting Started']")));
        appium.driver.findElement(By.xpath("//*[@text='Getting Started']")).click();
        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Post Student Activities']")));
        appium.driver.findElement(By.xpath("//*[@text='Post Student Activities']")).click();
        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='icon' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Nap']]]")));
        appium.driver.findElement(By.xpath("//*[@id='icon' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Nap']]]")).click();

        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='profile_image' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Alex Demo']]]")));
        appium.driver.findElement(By.xpath("//*[@id='profile_image' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Alex Demo']]]")).click();
        appium.driver.findElement(By.xpath("//*[@id='profile_image' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Mia Demo']]]")).click();
        appium.driver.findElement(By.xpath("//*[@text='NEXT']")).click();

        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Start Nap']")));
        appium.driver.findElement(By.xpath("//*[@text='Start Nap']")).click();
        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='CREATE']")));
        appium.driver.findElement(By.xpath("//*[@text='CREATE']")).click();

        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Post Student Activities']")));
        appium.driver.findElement(By.xpath("//*[@text='Post Student Activities']")).click();
        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='icon' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Nap']]]")));
        appium.driver.findElement(By.xpath("//*[@id='icon' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Nap']]]")).click();
        appium.driver.findElement(By.xpath("//*[@id='profile_image' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Alex Demo']] and (./preceding-sibling::* | ./following-sibling::*)[@id='activity_state_image']]")).click();
        appium.driver.findElement(By.xpath("//*[@id='profile_image' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Mia Demo']] and (./preceding-sibling::* | ./following-sibling::*)[@id='activity_state_image']]")).click();
        appium.driver.findElement(By.xpath("//*[@text='NEXT']")).click();
        appium.driver.findElement(By.xpath("//*[@text='End Nap']")).click();
        appium.driver.findElement(By.xpath("//*[@text='CREATE']")).click();

        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@contentDescription='Navigate up']")));
        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();

        this.sequences.logout();

        System.out.println("Brightwheel getting started test conpleted");
    }

    @Test
    public void roomsTest()
    {
        // This also shows how I quickly rough out a more complex test prior to refactoring it
        // into better selectors, page objects and sequences with much more interrogation

        System.out.println("Brightwheel room test starting");
        this.sequences.login("rhbourbonnais@yahoo.com", "brightwheelisgreat$1");

        appium.driver.findElement(By.xpath("//*[@text='Rooms']")).click();
        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Alex Demo']")));
        appium.driver.findElement(By.xpath("//*[@text='Alex Demo']")).click();
        new WebDriverWait(appium.driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@contentDescription='Navigate up']")));
        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();
        appium.driver.findElement(By.xpath("//*[@text='Mia Demo']")).click();
        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();

        new WebDriverWait(appium.driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='ATTENDANCE']")));
        appium.driver.findElement(By.xpath("//*[@text='ATTENDANCE']")).click();
        new WebDriverWait(appium.driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='toolbar']")));

        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();

        new WebDriverWait(appium.driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Rooms']")));
        appium.driver.findElement(By.xpath("//*[@text='Rooms']")).click();

        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='MESSAGES']")));
        appium.driver.findElement(By.xpath("//*[@text='MESSAGES']")).click();

        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='LEARNING']")));
        appium.driver.findElement(By.xpath("//*[@text='LEARNING']")).click();

        appium.driver.findElement(By.xpath("//*[@id='icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='Observation']]")).click();
        appium.driver.findElement(By.xpath("//*[@id='profile_image' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Alex Demo']] and (./preceding-sibling::* | ./following-sibling::*)[@id='checkin_state_image']]")).click();
        appium.driver.findElement(By.xpath("//*[@text='NEXT']")).click();

        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='comment_input']")));
        appium.driver.findElement(By.xpath("//*[@id='comment_input']")).sendKeys("doing good");
        new WebDriverWait(appium.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='CREATE']")));
        appium.driver.findElement(By.xpath("//*[@text='CREATE']")).click();

        new WebDriverWait(appium.driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Observation']")));
        appium.driver.findElement(By.xpath("//*[@text='Observation']")).click();
        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();
        appium.driver.findElement(By.xpath("//*[@id='icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='Lesson Plans']]")).click();
        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();
        appium.driver.findElement(By.xpath("//*[@text='CALENDAR']")).click();
        appium.driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();

        this.sequences.logout();

        System.out.println("Brightwheel room test conpleted");
    }
}
