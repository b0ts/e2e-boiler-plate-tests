package scenarios;

import org.testng.annotations.*;

import java.net.MalformedURLException;


public class AppiumTests extends TestBase {

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
        appium.launchApp();
    }
    @AfterMethod
    public void after() {
       appium.closeApp();
    }

    @Test
    public void openSettingsAppTest()
    {
        String settingsAppSelector = "//*[@contentDescription='Settings']";
        util.log("openSettingsAppTest starting");
        // note: creating a dummy app and then closing it is a hack to allow getting the OS during testing
        appium.closeApp();
        // once out in the OS, you can open any other app that is on the phone already
        appium.openAppFromOS(settingsAppSelector);
        util.log("openSettingsAppTest completed");
    }


}
