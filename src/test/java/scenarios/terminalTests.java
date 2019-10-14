package scenarios;

import org.testng.annotations.*;
import lib.AppiumDriverUtil;
import lib.ShellCommand;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.testng.Assert.assertEquals;

public class terminalTests extends TestBase {

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
    public void pythonRollDiceTest() {
        // python can be run as part of tests - typically used for test fixture control
        util.log("PythonRollDiceTest Started");

        int exitCode = this.cmd.rolldice();
        assertEquals(exitCode, 0, "Exit Code from Python not 0");

        util.log("PythonRollDiceTest Ended");
    }

    @Test
    public void pythonTestSuiteTest() {
        // Whole test suites of python tests can be also be run to test functionality via python
        util.log("PythonTestSuiteTest Started");

        int exitCode = this.cmd.testTheDevice();
        assertEquals(exitCode, 0, "Exit Code from Python not 0");

        util.log("PythonTestSuiteTest Ended");
    }

    @Test
    public void curlTest() {
        // curl can also be called - tupically used to set database and environment to known state and to retrieve
        // results after tests make changes.
        util.log("curlTest Started");
        String params = "-X POST -H 'Authorization: Bearer xoxb-1234-56789abcdefghijklmnop' \\\n" +
                "-H 'Content-type: application/json' \\\n" +
                "--data '{\"channel\":\"C061EG9SL\",\"text\":\"I hope the tour went well, Mr. Wonka.\",\"attachments\": [{\"text\":\"Who wins the lifetime supply of chocolate?\",\"fallback\":\"You could be telling the computer exactly what it can do with a lifetime supply of chocolate.\",\"color\":\"#3AA3E3\",\"attachment_type\":\"default\",\"callback_id\":\"select_simple_1234\",\"actions\":[{\"name\":\"winners_list\",\"text\":\"Who should win?\",\"type\":\"select\",\"data_source\":\"users\"}]}]}' \\\n"
                + "https://slack.com/api/chat.postMessage";
        int exitCode = this.cmd.curl(params);
        // expecting 2 because I didn't give the token to slack
        assertEquals(exitCode, 2, "Exit Code from curl not 0");

        util.pause();
        util.log("curlTest Ended");
    }

    @Test
    public void postmanTest() {
        // postman is the best way to execute a series of end points to test that they work, to set the application into a known state
        // and to retrieve app changes to backen after app is manipulated by Selenium or Appium.
        util.log("postmanTest Started");
        String parameters = "run /Users/shared/code/e2e-boiler-plate-tests/postman/PostmanEcho.postman";
        int exitCode = this.cmd.postman(parameters);
        assertEquals(exitCode, 0, "Exit Code from postman not 0");

        util.pause();
        util.log("postmanTest Ended");
    }

}
