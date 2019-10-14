package scenarios;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import org.testng.annotations.*;

import java.net.MalformedURLException;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

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
    public void aIsSweetLightStudiosActiveTest() {
        // RestAssured makes it easy to call end points and interrogate results
        // Note: We can do this with curl and postman as well - see the terminalTests for more
        // Running this test continuously and outputting failures to slack is a great way to
        // make a Nagios type monitor inexpensively
        util.log("aIsSweetLightStudiosActiveTest started");
        Response response = given().when().get("https://www.sweetlightstudios.com").andReturn();
        int code = response.getStatusCode();
        assertEquals(code, 200);
        util.log("aIsSweetLightStudiosActiveTest completed");
    }

    // These other REST tests are against a local server and fail if it isn't running
    @Test
    public void isLocalServerActiveTest() {
        util.log("isLocalServerActiveTest started");
        Response response = given().when().get("http://localhost:8080/app/greetings/Robert").andReturn();

        int code = response.getStatusCode();
        assertEquals(code, 200);
        util.log("isLocalServerActiveTest completed");
    }

    @Test
    public void greetingsTest() throws Exception {
        // use RestAssured to make an HTML Call
        util.log("greetingsTest started");
        Response response = RestAssured.get(
                "http://localhost:8080/app/greetings/Robert").
                andReturn();
        String json = response.getBody().asString();
        assertEquals(json, "\"Hello, Robert!\"");
        util.log("greetingsTest completed");
    }

    class Client{
        Client(String name, int height, int mass, String hair_color) {
            this.name = name;
            this.height = height;
            this.mass = mass;
            this.hair_color = hair_color;
        }
        String name;
        int height;
        int mass;
        String hair_color;
    }

    @Test
    public void getClientTest() throws Exception {
        util.log("getClientTest started");
        Response response = given().when().get("http://localhost:8080/app/getClient/Robert").andReturn();

        int code = response.getStatusCode();
        assertEquals(code, 200);

        String json = response.getBody().asString();

        // Use the JsonPath parsing library to Parse the JSON into an object
        Client client = new JsonPath(json).getObject("$", Client.class);

        // test fields in the object
        assertEquals(client.name, "Goober");
        util.log("getClientTest completed");
    }

    private class Reply {
        String results;
        String details;
    }

    @Test
    public void addClientTest() {
        util.log("addClientTest started");
        Client client = new Client("Goober Pyle", 610, 530, "Brown");
        Response response = given().contentType("application/json").body(client)
                .when().post("http://localhost:8080/app/addClient")
                .andReturn();

        String json = response.getBody().asString();

        Reply reply = new JsonPath(json).getObject("$", Reply.class);
        assertEquals(reply.results, "true");
        util.log("addClientTest completed");
    }

}
