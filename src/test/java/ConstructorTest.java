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
import pages.MainPage;
import tools.WebDriverFactory;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {

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
    @DisplayName("Проверка выбора булочек в конструкторе")
    public void clickBunsSelectionButton() {
        boolean isBunsSelected = new MainPage(driver)
                .clickFillingsSelectionButton()
                .clickBunsSelectionButton()
                .isBunsVisible();
        assertTrue("Булочки не выбраны", isBunsSelected);
        }

    @Test
    @DisplayName("Проверка выбора соусов в конструкторе")
    public void clickSousesSelectionButton() {
        boolean isSousesSelected = new MainPage(driver)
                .clickSousesSelectionButton()
                .isSousesVisible();
        assertTrue("Соусы не выбраны", isSousesSelected);
    }

    @Test
    @DisplayName("Проверка выбора начинок в конструкторе")
    public void clickFillingsSelectionButton() {
        boolean isFillingsSelected = new MainPage(driver)
                .clickFillingsSelectionButton()
                .isFillingsVisible();
        assertTrue("Начинка не выбрана", isFillingsSelected);
    }

}
