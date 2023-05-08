package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static config.Config.WEB_DRIVER_WAIT_SECONDS_TIMEOUT;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //локаторы
    private final By enterToAccountButton = By.xpath("/html/body/div/div/main/section[2]/div/button");
    private final By personalAreaButton = By.xpath("/html/body/div/div/header/nav/a");

    private final By checkoutOrderButton = By.xpath("/html/body/div/div/main/section[2]/div/button");

    private final By stallarisBurgerLogo = By.xpath("/html/body/div/div/header/nav/div/a/svg");





    //методы
    public LoginPage clickToAccountButton() {
        driver.findElement(enterToAccountButton).click();
        return new LoginPage(driver);
    }

    public AccountPage clickPersonalAreaButtonWhileAlreadyLogin() {
        driver.findElement(personalAreaButton).click();
        return new AccountPage(driver);
    }

    public LoginPage clickPersonalAreaButtonWhileIsNotLogin() {
        driver.findElement(personalAreaButton).click();
        return new LoginPage(driver);
    }

    public MainPage clickStellarisBurgerLogo() {
        driver.findElement(personalAreaButton).click();
        return new MainPage(driver);
    }


    //Возможно не нужный метод или просто его переделать
    public boolean isCheckoutOrderButtonVisible() {
        return driver.findElement(checkoutOrderButton).isDisplayed();
    }

}
