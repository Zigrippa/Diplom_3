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
import pages.RegistrationPage;
import tools.WebDriverFactory;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {

    private WebDriver driver;
    public UserClient userClient;
    public User user;
    public String accessToken;


    @Before
    public void setUp() {
        user = new User().generateUser();
        userClient = new UserClient();
        RestAssured.baseURI = ApiConfig.BASE_URL;
        driver = WebDriverFactory.get(Config.BROWSER_NAME, "reg");
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
    @DisplayName("Проверка регистрации с корректными данными")
    public void correctRegistrationTest() {
        boolean isLoginHeaderVisible = new RegistrationPage(driver)
                .inputEmail(user.getEmail())
                .inputName(user.getName())
                .inputPassword(user.getPassword())
                .clickRegistrationButton()
                .isLoginHeaderVisible();
        assertTrue("Регистрация не выполнена", isLoginHeaderVisible);
    }


    @Test
    @DisplayName("Проверка регистрации с недостаточно большим паролем")
    public void passwordLessThenSixSymbolsRegistrationTest() {
        user.setPassword("123");
        boolean isPasswordErrorVisible = new RegistrationPage(driver)
                .inputEmail(user.getEmail())
                .inputName(user.getName())
                .inputPassword(user.getPassword())
                .clickEmailField()
                .isIncorrectPasswordError();
        assertTrue("Ошибка о том, что пароль некорректен не появилась" +
                " и регистрация прошла успешно", isPasswordErrorVisible);
    }




}
