import api_models.User;
import api_models.UserClient;
import config.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import tools.WebDriverFactory;

import static org.junit.Assert.assertTrue;

public class PersonalAreaTest {

    private WebDriver driver;
    public UserClient userClient;
    public User user;
    public String accessToken;


    @Before
    public void setUp() {
        user = new User().generateUser();
        userClient = new UserClient();
        RestAssured.baseURI = ApiConfig.BASE_URL;
        driver = WebDriverFactory.get("chrome", "main");
        userClient.create(user);
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) userClient.delete(accessTokenExtraction(user));
    }

    public String accessTokenExtraction(User user) {
        ValidatableResponse response = userClient.login(user);
        return response.extract().path("accessToken");
    }

    @Test
    public void enterThroughPersonalAreaButtonToAccount() {
        boolean isProfileHeaderVisible = new MainPage(driver)
                .clickToAccountButton()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .clickPersonalAreaButtonWhileAlreadyLogin()
                .isProfileHeaderVisible();
        assertTrue(isProfileHeaderVisible);
    }


}
