import api_models.User;
import api_models.UserClient;
import config.ApiConfig;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import tools.WebDriverFactory;

import static org.junit.Assert.assertTrue;

public class EnterAccountTest {

    private WebDriver driver;
    public UserClient userClient;
    public User user;
    public String accessToken;


    @Before
    public void setUp() {
        user = new User().generateUser();
        userClient = new UserClient();
        RestAssured.baseURI = ApiConfig.BASE_URL;

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
    @DisplayName("Проверка входа через кнопку Войти в аккаунт")
    public void enterThroughEnterToAccountButton() {
        driver = WebDriverFactory.get("chrome", "main");
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new MainPage(driver)
                .clickToAccountButton()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }

    @Test
    @DisplayName("Проверка входа через кнопку Личный кабинет")
    public void enterThroughPersonalAreaButton() {
        driver = WebDriverFactory.get("chrome", "main");
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new MainPage(driver)
                .clickPersonalAreaButtonWhileIsNotLogin()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }


    @Test
    @DisplayName("Проверка входа через поле Зарегистрироваться и кнопку Войти")
    public void enterThroughToRegistrationButton() {
        driver = WebDriverFactory.get("chrome", "login");
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new LoginPage(driver)
                .clickToRegistrationButton()
                .clickEnterButton()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }


    @Test
    @DisplayName("Проверка входа через поле Восстановления аккаунта и кнопку Войти")
    public void enterThroughPasswordRecoverButton() {
        driver = WebDriverFactory.get("chrome", "login");
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new LoginPage(driver)
                .clickRecoverPasswordButton()
                .clickEnterButton()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }


}
