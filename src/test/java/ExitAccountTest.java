import api_models.User;
import api_models.UserClient;
import config.ApiConfig;
import config.Config;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import tools.WebDriverFactory;

import static org.junit.Assert.assertTrue;

public class ExitAccountTest {

    private WebDriver driver;
    public UserClient userClient;
    public User user;
    public String accessToken;


    @Before
    public void setUp() {
        user = new User().generateUser();
        userClient = new UserClient();
        RestAssured.baseURI = ApiConfig.BASE_URL;
        driver = WebDriverFactory.get(Config.BROWSER_NAME, "login");
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
    @DisplayName("Проверка выхода из аккаунта в личном кабинете")
    public void exitAccountButton() {
        boolean isCheckoutOrderButtonVisible = new LoginPage(driver)
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .clickPersonalAreaButtonWhileAlreadyLogin()
                .clickExitButton()
                .isLoginHeaderVisible();
        assertTrue("Выход из аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }

}
